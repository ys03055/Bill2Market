package com.example.demo.model.item;

public enum ItemQuality {

    HIGH(0),//상
    MIDDLE(1),//중
    LOW(2);//하

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

