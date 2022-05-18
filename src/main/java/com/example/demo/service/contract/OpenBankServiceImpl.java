package com.example.demo.service.contract;

import com.example.demo.config.OpenBankProperties;
import com.example.demo.exception.contract.*;
import com.example.demo.model.bank.*;
import com.example.demo.model.contract.Contract;
import com.example.demo.repository.BillyPayRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.ContractRepositoryCustom;
import com.example.demo.repository.RedisOpenBankRepository;
import com.example.demo.util.OpenBankUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenBankServiceImpl implements OpenBankService{

    private final BillyPayRepository billyPayRepository;
    private final ContractRepository contractRepository;
    private final RestTemplate restTemplate;
    private final Gson gson;
    private final OpenBankProperties openBankProperties;
    private final RedisOpenBankRepository redisOpenBankRepository;

    @Override
    public String getOpenBankOAuthUrl(Integer clientIndex, Integer contractId) {
        if (billyPayRepository.existsByClientIndex(clientIndex)){
            BillyPay billyPay = billyPayRepository.findByClientIndex(clientIndex).get();
            if(LocalDateTime.now().isAfter(billyPay.getExpireAt()))
                refreshOpenBankToken(billyPay);
            return "0";
        }
        return getAuthUrlFirst(clientIndex, contractId);
    }

    private String getAuthUrlFirst(Integer clientIndex, Integer contractId){
        String state = OpenBankUtil.getRandState();
        Contract contract = contractRepository.findById(contractId).orElseThrow(ContractNotFoundException::new);
        Boolean isLenter = contract.getChat().getLenter().getClientIndex() == clientIndex;
        redisOpenBankRepository.addClientInfo(clientIndex, new OpenBankClientInfo(state, isLenter));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("response_type", "code");
        params.add("client_id", openBankProperties.getClientId());
        params.add("redirect_uri", "http://localhost:8080/contracts/account/first/" + (isLenter? "lenter" : "owner"));
        params.add("scope", "login transfer");
        params.add("client_info", String.valueOf(clientIndex));
        params.add("state", state);
        params.add("auth_type", "0");
        params.add("lang", "kor");
        params.add("cellphone_cert_yn", "Y");
        params.add("authorized_cert_yn", "Y");
        params.add("account_hold_auth_yn", "N");

        URI uri = UriComponentsBuilder
                .fromUriString(openBankProperties.getBaseUrl())
                .path("/oauth/2.0/authorize")
                .queryParams(params)
                .encode()
                .build()
                .toUri();

        return uri.toString();
    }

    @Transactional
    public void registerUserInfoToken(String code, Integer clientIndex, String state){
        OpenBankClientInfo openBankClientInfo = redisOpenBankRepository.getClientInfo(clientIndex).orElseThrow(OpenBankCSRFErrorException::new);
        System.out.println(openBankClientInfo.toString());
        if(!openBankClientInfo.getState().equals(state)) throw new OpenBankCSRFErrorException();

        AccountTokenResponseDTO accountTokenResponseDTO = this.getOpenBankToken(code, openBankClientInfo.getIsLenter());
        AccountUserInfoResponseDTO accountUserInfoResponseDTO = this.getBankUserInfo(accountTokenResponseDTO);
        billyPayRepository.save(BillyPay.builder()
                .clientIndex(clientIndex)
                .accessToken(accountTokenResponseDTO.getAccess_token())
                .refreshToken(accountTokenResponseDTO.getRefresh_token())
                .expireAt(OpenBankUtil.getExpireDateTime(accountTokenResponseDTO.getExpires_in()))
                .fintechId(accountUserInfoResponseDTO.getRes_list().get(0).getFintech_use_num())
                .build());
    }

    private AccountTokenResponseDTO getOpenBankToken(String code, Boolean isLenter) {
        URI uri = UriComponentsBuilder
                .fromUriString(openBankProperties.getBaseUrl())
                .path("/oauth/2.0/token")
                .encode()
                .build()
                .toUri();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", openBankProperties.getClientId());
        params.add("client_secret", openBankProperties.getClientSecret());
        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", "http://localhost:8080/contracts/account/first/" + (isLenter? "lenter":"owner"));

        ResponseEntity<String> response = restTemplate.postForEntity(uri, params, String.class);
        AccountTokenResponseDTO accountTokenResponseDTO = null;
        if (response.getStatusCode() == HttpStatus.OK){
            System.out.println(response.getBody());
            accountTokenResponseDTO = gson.fromJson(response.getBody(), AccountTokenResponseDTO.class);
        } else{
            log.error(response.getBody());
            throw new OpenBankTokenErrorException();
        }
        return accountTokenResponseDTO;
    }

    private void refreshOpenBankToken(BillyPay billyPay) {
        URI uri = UriComponentsBuilder
                .fromUriString(openBankProperties.getBaseUrl())
                .path("/oauth/2.0/token")
                .encode()
                .build()
                .toUri();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", openBankProperties.getClientId());
        params.add("client_secret", openBankProperties.getClientSecret());
        params.add("grant_type", "refresh_token");
        params.add("refresh_token", billyPay.getRefreshToken());

        ResponseEntity<String> response = restTemplate.postForEntity(uri, params, String.class);

        if (response.getStatusCode() == HttpStatus.OK){
            AccountTokenResponseDTO accountTokenResponseDTO = gson.fromJson(response.getBody(), AccountTokenResponseDTO.class);

            billyPay.setAccessToken(accountTokenResponseDTO.getAccess_token());
            billyPay.setRefreshToken(accountTokenResponseDTO.getRefresh_token());
            billyPay.setExpireAt(OpenBankUtil.getExpireDateTime(accountTokenResponseDTO.getExpires_in()));
            billyPayRepository.save(billyPay);
        }else{
            throw new OpenBankTokenErrorException();
        }
    }

    private AccountUserInfoResponseDTO getBankUserInfo(AccountTokenResponseDTO accountTokenResponseDTO){
        URI uri = UriComponentsBuilder
                .fromUriString(openBankProperties.getBaseUrl())
                .path("/v2.0/user/me")
                .queryParam("user_seq_no", accountTokenResponseDTO.getUser_seq_no())
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accountTokenResponseDTO.getAccess_token());
        HttpEntity<MultiValueMap<String, String>> requestHeaders = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, requestHeaders, String.class);
        AccountUserInfoResponseDTO accountUserInfoResponseDTO = null;
        if(response.getStatusCode() == HttpStatus.OK){
            accountUserInfoResponseDTO = gson.fromJson(response.getBody(), AccountUserInfoResponseDTO.class);
        }else{
            throw new OpenBankUserInfoException();
        }

        return accountUserInfoResponseDTO;
    }

    @Transactional
    public PaymentResultResponseDTO transfer(Integer clientIndex, TransferRequestDTO transferRequestDTO){
        BillyPay billyPay = billyPayRepository.findByClientIndex(clientIndex).orElseThrow();
        Contract contract = contractRepository.findById(transferRequestDTO.getContractId()).orElseThrow(ContractNotFoundException::new);

        URI uri = UriComponentsBuilder
                .fromUriString(openBankProperties.getBaseUrl())
                .path("/v2.0/transfer/withdraw/fin_num")
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + billyPay.getAccessToken());

        WithdrawalInfo withdrawalInfo = WithdrawalInfo.builder()
                .bank_tran_id(OpenBankUtil.getRandBankTranId(openBankProperties.getInstitutionCode()))
                .cntr_account_type("N")
                .cntr_account_num(openBankProperties.getAccountNum())
                .dps_print_content("빌리페이")
                .fintech_use_num(billyPay.getFintechId())
                .req_client_name(openBankProperties.getName())
                .req_client_bank_code(openBankProperties.getBankCode())
                .req_client_account_num(openBankProperties.getAccountNum())
                .tran_amt(String.valueOf(transferRequestDTO.paymentAmount()))
                .tran_dtime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .req_client_num("BILL2MARKET" + clientIndex)
                .transfer_purpose("TR")
                .recv_client_name(openBankProperties.getName())
                .recv_client_bank_code(openBankProperties.getBankCode())
                .recv_client_account_num(openBankProperties.getAccountNum())
                .build();

        HttpEntity<String> requestParams = new HttpEntity<String>(gson.toJson(withdrawalInfo), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestParams, String.class);

        if (response.getStatusCode() == HttpStatus.OK){
            WithdrawalResponseDTO withdrawalResponseDTO = gson.fromJson(response.getBody(), WithdrawalResponseDTO.class);
            System.out.println(withdrawalResponseDTO.toString());
            contract.setPrice(transferRequestDTO.getPrice());
            contract.setDeposit(transferRequestDTO.getDeposit());
            contractRepository.save(contract);

            return new PaymentResultResponseDTO(
                    contract.getContractId(),
                    Integer.parseInt(withdrawalResponseDTO.getTran_amt()),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                );
        }else{
            log.error(response.getBody());
            throw new OpenBankTransferErrorException();
        }
    }

}

