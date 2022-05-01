package com.example.demo.model.chat;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class ChatMessage {

    public enum ChatType{
        ENTER, MESSAGE, EXIT
    }

    private ChatType chatType;
    private Integer chatId;
    private Integer senderId;
    private String senderNickname;
    private String message;
    private Boolean isRead;
    private MessageType messageType;
    private String createDate;

    public void setMessageType(int type){
        this.messageType = MessageType.values()[type];
    }

}
