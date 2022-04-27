package com.example.demo.model.item;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class ItemOwnerResponseDTO {

    private String itemTitle;
    private Integer price;
    private Integer deposit;
    private String itemAddress;
    private boolean is_main;
    private ContractStatus contractStatus;
    private LocalDate createDate;

}
