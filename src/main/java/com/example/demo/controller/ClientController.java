
package com.example.demo.controller;

import com.example.demo.exception.client.ClientNotFoundException;
import com.example.demo.exception.client.ExistIdException;
import com.example.demo.exception.client.ExistNicknameException;
import com.example.demo.model.client.Client;
import com.example.demo.model.client.SignupRequest;
import com.example.demo.model.response.CommonResult;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.Client.ClientService;
import com.example.demo.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. Client"})
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ClientRepository clientRepository;
    private final ResponseService responseService;
    private final ClientService clientService;

    @ApiOperation(value = "사용자 정보", notes = "사용자 정보를 조회한다.")
    @GetMapping("/clients/me")
    public CommonResult myInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return responseService.getSingleResult(
                clientRepository.findById(Integer.parseInt(auth.getName())).orElseThrow(ClientNotFoundException::new));
    }

    @ApiOperation(value = "ID중복 체크", notes = "회원가입 시 ID중복 체크")
    @GetMapping("/clients/id-check")
    public CommonResult checkId(@RequestParam String clientId){
        if(clientRepository.findByClientId(clientId).isPresent()) throw new ExistIdException(); //이미 존재하는 ID exception
        return responseService.getSuccessfulResult();
    }

    @ApiOperation(value = "닉네임 중복 체크", notes = "회원가입 시 닉네임 중복 체크")
    @GetMapping("/clients/nickname-check")
    public CommonResult checkNickname(@RequestParam String nickname){//이미 존재하는 Nickname있을 때 exception처리
        if(clientRepository.findByNickname(nickname).isPresent()) throw new ExistNicknameException();
        return responseService.getSuccessfulResult();
    }

    @ApiOperation(value = "닉네임 설정", notes = "SNS 회원가입 시 사용자의 닉네임을 설정한다.")
    @PutMapping("/clients/{client-index}/nickname")
    public CommonResult setNickname(@PathVariable("client-index") Integer clientIndex, @RequestParam("nickname")String nickname){
        System.out.println(clientIndex);
        System.out.println(nickname);
        clientService.setNickname(clientIndex, nickname);
        return responseService.getSuccessfulResult();
    }

}