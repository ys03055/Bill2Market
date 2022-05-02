package com.example.demo.model.chat;

import com.example.demo.model.item.OwnerInfo;
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
@NamedNativeQuery(
        name = "chatList",
        query = "SELECT Chat.chat_id, Chat.lenter_index, Chat.owner_index, Chat.fileName " +
                "CASE WHEN Chat.owner_index = :client_index THEN  Chat.owner_index = owner_index " +
                "CASE WHEN Chat.lenter_index = :client_index THEN  Chat.lenter_index = lenter_index " +
                "FROM Chat " +
                "WHERE Chat.owner_index = :client_index OR Chat.lenter_index = :client_index ",
        resultSetMapping = "chatListMapping"
)
@SqlResultSetMapping(
        name = "chatListMapping",
        classes = @ConstructorResult(
                targetClass = ChatListResponseDTO.class,
                columns = {
                        @ColumnResult(name = "chat_id", type = Integer.class),
                        @ColumnResult(name = "lenter_index", type = Integer.class),
                        @ColumnResult(name = "owner_index", type = Integer.class),
                        @ColumnResult(name = "file_name", type = String.class)
                }
        )
)
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