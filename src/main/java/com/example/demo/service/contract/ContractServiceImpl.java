package com.example.demo.service.contract;

import com.example.demo.exception.chat.ChatNotFoundException;
import com.example.demo.exception.contract.ContractNotFoundException;
import com.example.demo.model.chat.Chat;
import com.example.demo.model.contract.*;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ContractServiceImpl implements ContractService{

    private final ContractRepository contractRepository;
    private final ChatRepository chatRepository;

    @Override
    public Contract addContract(ContractRequestDTO contractRequestDTO) {
        Chat chat = chatRepository.findById(contractRequestDTO.getChatId()).orElseThrow(ChatNotFoundException::new);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return contractRepository.save(Contract.builder()
                                        .chat(chat)
                                        .startDate(LocalDate.parse(contractRequestDTO.getStartDate(), formatter))
                                        .endDate(LocalDate.parse(contractRequestDTO.getEndDate(), formatter))
                                        .contractStatus(ContractType.REQUEST)
                                        .build());
    }

    @Override
    public Contract getContract(Integer contractId) {
        return contractRepository.findById(contractId).orElseThrow(ContractNotFoundException::new);
    }

    @Override
    public Contract modifyContract(Integer contractId, Integer contractStatus) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(ContractNotFoundException::new);
        contract.setContractStatus(ContractType.values()[contractStatus]);
        contract.setContractDate(LocalDate.now());
        return contractRepository.save(contract);
    }

    @Override
    public Contract modifyContract(Integer contractId, String endDate) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(ContractNotFoundException::new);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        contract.setEndDate(LocalDate.parse(endDate, formatter));
        return contractRepository.save(contract);
    }

    @Override
    public Slice<ContractIBorrowed> findBorrowedItemList(Integer clientIndex, Integer page) {

        return contractRepository.findContractsByClientIndex(clientIndex, PageRequest.of(page, 10));
    }

}