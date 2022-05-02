package com.example.demo.model.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChatListResponseDTO {

    private Integer ownerIndex;
    private Integer chatId;
    private Integer lenterIndex;
    private String fileName ;

}