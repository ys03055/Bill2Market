package com.example.demo.model.item;

public enum ItemQuality {

    HIGH(2),//상
    MIDDLE(1),//중
    LOW(0);//하

    private final int value;
    private ItemQuality(int value){
        this.value = value;
    }

    private int getValue(){
        return value;
    }

}

