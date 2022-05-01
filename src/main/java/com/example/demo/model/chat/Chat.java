package com.example.demo.model.chat;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Chat")
public class Chat {
    @Id
    @Column(name = "chat_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;
    @Column(name = "lenter_index", nullable = false)
    private Integer lenterIndex;
    @Column(name = "owner_index", nullable = false)
    private Integer ownerIndex;
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "create_date", nullable = false)
    private Date createDate;

}