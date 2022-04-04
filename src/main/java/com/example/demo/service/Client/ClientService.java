package com.example.demo.service.Client;

import com.example.demo.model.response.CommonResult;

public interface ClientService {

    public void setNickname(int clientIndex, String nickName);
    public CommonResult getClientFromNaver(String naverToken);
}
