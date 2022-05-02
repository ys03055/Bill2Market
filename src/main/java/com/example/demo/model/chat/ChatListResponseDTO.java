package com.example.demo.model.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChatListResponseDTO {

    private Integer chatId;
    private Integer opponentIndex;
    private String fileName ;
    private String nickname;

}