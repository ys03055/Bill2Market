package com.example.demo.model.item;

public enum ContractStatus{

    GENERAL(0),//예약 가능
    RESERVATION(1),//예약중
    RENTAL(2),//사용중
    NORESERVATION(3);//예약 불가 - endDate가 넘어가면 예약불가 상태로 바뀌게 구현

    private final int value;
    private ContractStatus(int value){
        this.value = value;
    }

    private int getValue(){
        return value;
    }

    public int getContractStatusValue(){
        ContractStatus contractStatus = ContractStatus.GENERAL;
        return contractStatus.getValue();
    }
}
