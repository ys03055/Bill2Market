package com.example.demo.model.item;

public enum ContractStatus{

    GENERAL,//예약 가능
    RESERVATION,//예약중
    RENTAL,//사용중
    NORESERVATION;//예약 불가 - endDate가 넘어가면 예약불가 상태로 바뀌게 구현

}