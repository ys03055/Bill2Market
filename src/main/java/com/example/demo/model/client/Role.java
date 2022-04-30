package com.example.demo.model.client;

import com.example.demo.model.item.ContractStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum Role {

    USER(0),
    MANAGER(1);

    private final int value;
    private Role(int value){
        this.value = value;
    }

    private int getValue(){
        return value;
    }

    public int getRoleValue(){
        Role role = Role.USER;
        return role.getValue();
    }

}
