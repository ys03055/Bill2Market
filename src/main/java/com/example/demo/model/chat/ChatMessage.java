package com.example.demo.model.chat;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class ChatMessage {

    public enum MessageType{
        ENTER, MESSAGE, EXIT
    }

    private MessageType messageType;
    private Integer chatId;
    private Integer senderId;
    private String senderNickname;
    private String message;
    private Boolean isRead;
    private Integer isImg;
    private String createDate;

}
