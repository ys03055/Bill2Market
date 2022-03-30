package com.example.demo.model.chat;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Chat_Photo")
public class Chat_Photo {
    @Id
    private int chat_id;

    @Column(nullable = false)
    private String chat_photo;
}
