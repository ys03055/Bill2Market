package com.example.demo.controller;

import com.example.demo.config.jwt.JwtTokenProvider;
import com.example.demo.model.chat.ChatMessage;
import com.example.demo.model.chat.ChatMessageEvent;
import com.example.demo.repository.RedisChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisChatRepository redisChatRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @MessageMapping("/chat/message")
    public void message(ChatMessage chatMessage, @Header("Authorization") String token){
        long userCount = redisChatRepository.getUserCount(String.valueOf(chatMessage.getChatId()));
        Authentication auth = jwtTokenProvider.getAuthentication(token);
        chatMessage.setSenderId(Integer.parseInt(auth.getName()));
        chatMessage.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if(userCount == 1){
            chatMessage.setIsRead(false);
            redisChatRepository.plusNonReadCount(String.valueOf(chatMessage.getChatId()));
        }else if(userCount == 2){
            chatMessage.setIsRead(true);
            redisChatRepository.resetNonReadCount(String.valueOf(chatMessage.getChatId()));
        }

        applicationEventPublisher.publishEvent(new ChatMessageEvent(applicationEventPublisher, chatMessage));
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }

}
