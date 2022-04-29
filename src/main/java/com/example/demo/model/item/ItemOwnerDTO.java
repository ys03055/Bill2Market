package com.example.demo.model.item;

import lombok.Getter;

import java.time.LocalDate;

public interface ItemOwnerDTO {
    Integer clientIndex();
    String itemTitle();
    Integer price();
    Integer deposit();
    String itemAddress();
    boolean is_main();
    ContractStatus contractStatus();
    LocalDate createDate();

}
