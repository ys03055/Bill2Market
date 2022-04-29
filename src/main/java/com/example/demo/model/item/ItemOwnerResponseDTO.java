package com.example.demo.model.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Data
public class ItemOwnerResponseDTO {

    private Integer clientIndex;
    private String itemTitle;
    private Integer price;
    private Integer deposit;
    private String itemAddress;
    private boolean is_main;
    private ContractStatus contractStatus;
    private LocalDate createDate;

    public ItemOwnerResponseDTO(Integer clientIndex, String itemTitle, Integer price, Integer deposit, String itemAddress,
                                boolean is_main, ContractStatus contractStatus, LocalDate createDate){
        this.clientIndex = clientIndex;
        this.itemTitle = itemTitle;
        this.price = price;
        this.deposit = deposit;
        this.itemAddress = itemAddress;
        this.is_main = is_main;
        this.contractStatus = contractStatus;
        this.createDate = createDate;

    }


}
