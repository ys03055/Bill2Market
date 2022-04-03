
package com.example.demo.controller;

import com.example.demo.exception.client.ClientNotFoundException;
import com.example.demo.model.client.Client;
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
    @GetMapping("/client/me")
    public CommonResult myInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return responseService.getSingleResult(
                clientRepository.findById(Integer.parseInt(auth.getName())).orElseThrow(ClientNotFoundException::new));
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
