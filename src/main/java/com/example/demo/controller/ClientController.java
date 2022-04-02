//package com.example.demo.controller;
//
//import com.example.demo.exception.client.ClientNotFoundException;
//import com.example.demo.model.client.Client;
//import com.example.demo.model.client.LoginRequest;
//import com.example.demo.model.response.CommonResult;
//import com.example.demo.repository.ClientRepository;
//import com.example.demo.service.ResponseService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@Api(tags = {"2. Client"})
//@RequiredArgsConstructor
//@RestController
//public class ClientController {
//
//    private final ClientRepository clientRepository;
//    private final ResponseService responseService;
//
//    @ApiOperation(value = "사용자 정보", notes = "사용자 정보를 조회한다.")
//    @GetMapping("/client/me")
//    public CommonResult myInfo(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return responseService.getSingleResult(
//                clientRepository.findById(auth.getName()).orElseThrow(ClientNotFoundException::new));
//    }
//
////    @ApiOperation(value = "마이 페이지", notes = "마이 페이지를 보여준다.")
////    @GetMapping("/client/{client_index}")
////    public CommonResult myPage(LoginRequest loginRequest){
////
////
////
////        return responseService.getSuccessfulResult();
////
////
////    }
//
//
//}
