package com.example.demo.model.item;


import lombok.Data;

@Data
public class OwnerInfo{
    private String nickname;
    private Float trustPoint;

    public OwnerInfo(String nickname, Float trustPoint){
        this.nickname = nickname;
        this.trustPoint = trustPoint;
    }
}
