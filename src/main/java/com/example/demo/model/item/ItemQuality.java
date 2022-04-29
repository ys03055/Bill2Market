package com.example.demo.model.item;

public enum ItemQuality {

    HIGH(0),
    MIDDLE(1),
    LOW(2);

    private final int value;
    private ItemQuality(int value){
        this.value = value;
    }

    private int getValue(){
        return value;
    }

    public int getItemQualityValue(){
        ItemQuality itemQuality = ItemQuality.MIDDLE;
        return itemQuality.getValue();
    }
}

