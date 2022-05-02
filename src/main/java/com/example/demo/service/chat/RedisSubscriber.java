package com.example.demo.service.chat;

import com.example.demo.model.chat.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messageTemplate;

    public void sendMessage(String pubMessage){
        try {
            ChatMessage chatMessage = objectMapper.readValue(pubMessage, ChatMessage.class);
            messageTemplate.convertAndSend("/sub/chat/" + chatMessage.getChatId(), chatMessage);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

}
