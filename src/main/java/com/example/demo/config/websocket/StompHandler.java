package com.example.demo.config.websocket;

import com.example.demo.service.chat.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

    private final MessageService messageService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        accessor.setLeaveMutable(false);
        if(StompCommand.CONNECT == accessor.getCommand())
            messageService.connect(message, accessor);
        else if(StompCommand.SUBSCRIBE == accessor.getCommand())
            messageService.subscribe(message, accessor);
        else if(StompCommand.DISCONNECT == accessor.getCommand())
            messageService.disconnect(message, accessor);
        else if (StompCommand.UNSUBSCRIBE == accessor.getCommand())
            messageService.unSubscribe(message, accessor);

        return message;
    }

}
