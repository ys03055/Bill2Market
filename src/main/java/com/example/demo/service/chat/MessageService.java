package com.example.demo.service.chat;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public interface MessageService {

    public void connect(Message<?> message, StompHeaderAccessor accessor);
    public void subscribe(Message<?> message, StompHeaderAccessor accessor);
    public void unSubscribe(Message<?> message, StompHeaderAccessor accessor);
    public void disconnect(Message<?> message, StompHeaderAccessor accessor);

}
