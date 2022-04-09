package com.example.demo.model.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@ToString
@Data
public class SimpleItem {

    private Integer itemId;
    private String itemTitle;
    private String itemAddress;
    private Integer price;
    private Integer deposit;
    private String itemPhoto;
    private ItemStatus itemStatus;
    private Date createDate;
    private Boolean isBasket;

}
