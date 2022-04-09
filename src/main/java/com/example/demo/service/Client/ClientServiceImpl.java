package com.example.demo.service.Client;

import com.example.demo.config.jwt.JwtTokenProvider;
import com.example.demo.exception.common.HttpFailException;
import com.example.demo.model.client.Client;
import com.example.demo.model.client.Role;
import com.example.demo.model.response.CommonResult;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;
    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void setNickname(int clientIndex, String nickName) {
        Client client = new Client();
        client.setClientIndex(clientIndex);
        client.setNickname(nickName);
        clientRepository.save(client);
    }

    @Override
    public CommonResult getClientFromNaver(String naverToken) {
        try {
            HttpGet httpGet = new HttpGet("https://openapi.naver.com/v1/nid/me");
            httpGet.setHeader("Authorization", "BEARER " + naverToken);

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if(response.getStatusLine().getStatusCode() == 200){
                ResponseHandler<String> handler = new BasicResponseHandler();

                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(handler.handleResponse(response), Map.class);
                Map<String, String> clientInfo = (Map<String, String>)map.get("response");

                Client client = clientRepository.findByPhoneNumber(clientInfo.get("mobile"));
                Date date = Date.valueOf(clientInfo.get("birthyear") + "-" + clientInfo.get("birthday"));

                if(client == null){ // 회원가입
                    client = Client.builder()
                            .email(clientInfo.get("email"))
                            .phoneNumber(clientInfo.get("mobile"))
                            .clientName(clientInfo.get("name"))
                            .birthdate(date)
                            .snsType(2)
                            .role(Role.USER)
                            .build();
                    clientRepository.save(client);
                    return responseService.getNeedNickname();
                }else{ // 로그인
                    Map<String, Object> loginMap = new HashMap<>();
                    return responseService.getLoginResponse(jwtTokenProvider.createToken(client.getClientIndex(), client.getRole()), client.getClientIndex());
                }
            }else{
                throw new HttpFailException();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new HttpFailException();
        }
    }
}
