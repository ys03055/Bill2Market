package com.example.demo.controller;

import com.example.demo.model.item.ItemSaveRequestDTO;
import com.example.demo.model.item.ItemSearchRequestDTO;
import com.example.demo.model.response.CommonResult;
import com.example.demo.repository.ClientRepository;
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

    private final ClientRepository clientRepository;
    private final ResponseService responseService;
    private final ItemService itemService;

    @ApiOperation(value = "기본 물품 리스트 조회", notes = "사용자와의 거리에 따른 물품 리스트를 조회한다.")
    @GetMapping("")
    public CommonResult itemList(ItemSearchRequestDTO itemSearchRequestDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return responseService.getSingleResult(itemService.findItemList(itemSearchRequestDTO, (!auth.getName().equals("anonymousUser"))? Integer.parseInt(auth.getName()) : -1000));
    }

    @ApiOperation(value = "기본 물품 상세 조회", notes = "번호에 맞는 물품을 조회한다.")
    @GetMapping("/{item-id}")
    public CommonResult itemDetail(@PathVariable("item-id") Integer itemId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return responseService.getSingleResult(itemService.findItemOne(itemId, (!auth.getName().equals("anonymousUser"))? Integer.parseInt(auth.getName()) : -1000));
    }

    @ApiOperation(value = "물품 리뷰 조회", notes = "해당 물품의 리뷰를 조회한다.")
    @GetMapping("/{item-id}/review")
    public CommonResult itemReview(@PathVariable("item-id") Integer itemId, @RequestParam Integer page){
        return responseService.getSingleResult(itemService.findItemReview(itemId, page));
    }

    @ApiOperation(value = "게시물 저장", notes = "게시물 저장")
    @PostMapping("")
    public CommonResult itemSave(@RequestPart(value = "item") ItemSaveRequestDTO itemSaveRequest,
                                 @RequestPart(value = "itemPhoto") List<MultipartFile> itemPhotoSaveRequest) throws IOException {
        itemService.saveItem(itemSaveRequest, itemPhotoSaveRequest);
        return responseService.getSuccessfulResult();
    }

    @ApiOperation(value="게시물 검색", notes = "키워드 검색을 통해 게시물을 검색하여 물품 리스트를 조회한다.")
    @GetMapping("/search-keyword")
    public CommonResult searchItemList(ItemSearchRequestDTO itemSearchRequestDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return responseService.getSingleResult(itemService.findItemByQuery(itemSearchRequestDTO, (!auth.getName().equals("anonymousUser"))? Integer.parseInt(auth.getName()) : -1000));
    }


}
