package com.example.demo.model.item;

import lombok.Data;

@Data
public class ItemSaveRequest {
    private String categoryBig;
    private String categoryMiddle;
    private String categorySmall;
    private String itemTitle;
    private String itemContent;
    private String itemQuality;
    private int price;
    private int deposit;
    private String itemAddress;
    private double itemLatitude;
    private double itemLongitude;
    private Integer ownerId;
    private ContractStatus contractStatus;


    public Item toEntity(){
        return Item.builder()
                .ownerId(ownerId)
                .itemTitle(itemTitle)
                .categoryBig(categoryBig)
                .categoryMiddle(categoryMiddle)
                .categorySmall(categorySmall)
                .itemContent(itemContent)
                .itemQuality(itemQuality)
                .price(price)
                .deposit(deposit)
                .itemAddress(itemAddress)
                .views(0)
                .contractStatus(contractStatus)
                .build();

    }


}
