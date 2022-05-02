package com.example.demo.service.chat;

import com.example.demo.model.chat.ChatListResponseDTO;

import java.util.List;

public interface ChatService {

    public List<ChatListResponseDTO> findClientChatList(Integer clientIndex);
}
