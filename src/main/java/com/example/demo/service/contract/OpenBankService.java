package com.example.demo.service.contract;

import com.example.demo.model.bank.PaymentResultResponseDTO;
import com.example.demo.model.bank.TransferRequestDTO;

public interface OpenBankService {
    public String getOpenBankOAuthUrl(Integer clientIndex, Integer contractId);
    public void registerUserInfoToken(String code, Integer clientIndex, String state);
    public PaymentResultResponseDTO transfer(Integer clientIndex, TransferRequestDTO transferRequestDTO);
}
