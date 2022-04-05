package com.example.demo.model.item;

import lombok.Data;

@Data
public class ItemSearchRequestDTO {

    private double longitude;
    private double latitude;
    private Integer page;

}
