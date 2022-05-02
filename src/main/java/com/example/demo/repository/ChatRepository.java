package com.example.demo.repository;

import com.example.demo.model.chat.Chat;
import com.example.demo.model.chat.ChatListResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {

    @Query(name = "chatList", nativeQuery = true)
    public List<ChatListResponseDTO> findChatByClientIndex(@Param("client_index") Integer clientIndex, Integer ownerIndex);
}