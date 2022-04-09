package com.example.demo.controller;

import com.example.demo.exception.item.ItemNotFoundException;
import com.example.demo.model.item.ItemSearchRequestDTO;
import com.example.demo.model.item.ItemSaveRequest;
import com.example.demo.model.response.CommonResult;
import com.example.demo.service.ResponseService;
import com.example.demo.service.item.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = {"3. Item"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ResponseService responseService;
    private final ItemService itemService;

    @ApiOperation(value = "기본 물품 리스트 조회", notes = "사용자와의 거리에 따른 물품 리스트를 조회한다.")
    @GetMapping("")
    public CommonResult itemList(ItemSearchRequestDTO itemSearchRequestDTO){
        return responseService.getSingleResult(itemService.findItemList(itemSearchRequestDTO));
    }

    @ApiOperation(value = "기본 물품 상세 조회", notes = "번호에 맞는 물품을 조회한다.")
    @GetMapping("/{item-index}")
    public CommonResult itemDetail(@PathVariable("item-index") Integer itemIndex){
        return responseService.getSingleResult(itemService.findItemOne(itemIndex).orElseThrow(ItemNotFoundException::new));
    }
    
    @ApiOperation(value = "임시 게시물 저장", notes = "임시 게시물 저장")
    @PostMapping("/item")
    public CommonResult itemSave(@RequestPart(value = "item") ItemSaveRequest itemSaveRequest,
                                 @RequestPart(value = "itemPhoto") List<MultipartFile> itemPhotoSaveRequest) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        itemSaveRequest.setOwnerId(Integer.parseInt(auth.getName()));
        itemService.saveItem(itemSaveRequest, itemPhotoSaveRequest);
        return responseService.getSuccessfulResult();
    }


}
