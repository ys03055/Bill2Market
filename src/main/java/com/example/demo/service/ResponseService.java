package com.example.demo.service;

import com.example.demo.model.response.CommonResult;
import com.example.demo.model.response.ListResult;
import com.example.demo.model.response.LoginResponse;
import com.example.demo.model.response.SingleResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @AllArgsConstructor
    @Getter
    public enum CommonResponse {
        SUCCESS(0, "성공하였습니다."),
        NEED_NICKNAME(1, "닉네임 입력 필요");

        int code;
        String message;

    }

    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<T>();
        result.setData(data);
        this.setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<T>();
        result.setList(list);
        this.setSuccessResult(result);
        return result;
    }

    public <T> LoginResponse<T> getLoginResponse(T token, T clientIndex){
        LoginResponse<T> result = new LoginResponse<>();
        result.setToken(token);
        result.setClientIndex(clientIndex);
        this.setSuccessResult(result);
        return result;
    }

    public CommonResult getSuccessfulResult() {
        CommonResult result = new CommonResult();
        this.setSuccessResult(result);
        return result;
    }

    public CommonResult getNeedNickname(){
        CommonResult result = new CommonResult();
        this.setNeedNicknameResult(result);
        return result;
    }

    public CommonResult getFailResult(int code, String message) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMessage());
    }

    private void setNeedNicknameResult(CommonResult result){
        result.setSuccess(false);
        result.setCode(CommonResponse.NEED_NICKNAME.getCode());
        result.setMessage(CommonResponse.NEED_NICKNAME.getMessage());
    }

}
