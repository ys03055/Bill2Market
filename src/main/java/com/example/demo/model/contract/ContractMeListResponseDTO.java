package com.example.demo.model.contract;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class ContractMeListResponseDTO {

    private String itemTitle;
    private Integer price;
    private Integer deposit;
    private String itemAddress;
    private Boolean isMain;
    private String contractStatus;
    private LocalDate startDate;
    private LocalDate endDate;

}