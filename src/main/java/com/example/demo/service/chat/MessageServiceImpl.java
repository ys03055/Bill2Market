package com.example.demo.service.chat;

import com.example.demo.config.jwt.JwtTokenProvider;
import com.example.demo.exception.chat.ChatNotFoundException;
import com.example.demo.model.chat.Chat;
import com.example.demo.model.chat.ChatMessage;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.RedisChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService{

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisChatRepository redisChatRepository;
    private final ChatRepository chatRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;

    @Override
    public void connect(Message<?> message, StompHeaderAccessor accessor) {
        jwtTokenProvider.validateToken(accessor.getFirstNativeHeader("Authorization"));
    }

    @Override
    public void subscribe(Message<?> message, StompHeaderAccessor accessor) {
        Authentication auth = jwtTokenProvider.getAuthentication(accessor.getFirstNativeHeader("Authorization"));
        String clientIndex = auth.getName();
        String chatId = getChatId(Optional.ofNullable((String) message.getHeaders().get("simpDestination")).orElse("InvalidRoomId"));
        String lastUser = redisChatRepository.getLastUser(chatId);
        String sessionId = (String) message.getHeaders().get("simpSessionId");
        redisChatRepository.addUser(chatId, clientIndex, sessionId);

        if(!lastUser.equals(clientIndex))// 현재 접속자가 마지막 접속자가 아닌 경우
            redisChatRepository.resetNonReadCount(chatId);

        ChatMessage chatMessage = ChatMessage.builder()
                .messageType(ChatMessage.MessageType.ENTER)
                .chatId(Integer.parseInt(chatId))
                .senderId(Integer.parseInt(clientIndex))
                .isRead(false)
                .build();

        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }

    @Override
    public void unSubscribe(Message<?> message, StompHeaderAccessor accessor) {
        String[] chatIdAndToken = accessor.getFirstNativeHeader("id").split("/");
        Authentication auth = jwtTokenProvider.getAuthentication(chatIdAndToken[1]);
        String clientIndex = auth.getName();
        String chatId = chatIdAndToken[0];
        redisChatRepository.deleteUser(chatId, clientIndex);

        ChatMessage chatMessage = ChatMessage.builder()
                .messageType(ChatMessage.MessageType.EXIT)
                .chatId(Integer.parseInt(chatId))
                .senderId(Integer.parseInt(clientIndex))
                .build();

        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);

    }

    @Override
    public void disconnect(Message<?> message, StompHeaderAccessor accessor) {
        
    }

    private String getChatId(String destination){
        int lastIndex = destination.lastIndexOf('/');
        return (lastIndex != -1)? destination.substring(lastIndex + 1) : "";
    }
}
