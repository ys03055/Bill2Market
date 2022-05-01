package com.example.demo.service.chat;

import com.example.demo.exception.client.ClientNotFoundException;
import com.example.demo.model.chat.ChatListResponseDTO;
import com.example.demo.model.client.Client;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService{
    private final ClientRepository clientRepository;
    private final ChatRepository chatRepository;


    @Override
    public List<ChatListResponseDTO> findClientChatList(Integer clientIndex, Integer ownerId) {
        Client client = clientRepository.findById(clientIndex).orElseThrow(ClientNotFoundException::new);
        return chatRepository.findChatByClientIndex(ownerId);
    }
}
