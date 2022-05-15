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
        query = "SELECT Chat.item_id, Chat.chat_id, Chat.file_name, Client.nickname, " +
                "CASE WHEN Chat.owner_index = :client_index THEN Chat.lenter_index " +
                "ELSE Chat.owner_index END as opponentIndex " +
                "FROM Chat " +
                "INNER JOIN Client ON Client.client_index = CASE WHEN Chat.owner_index = :client_index THEN Chat.lenter_index " +
                "ELSE Chat.owner_index END " +
                "WHERE Chat.owner_index = :client_index OR Chat.lenter_index = :client_index " +
                "ORDER BY Chat.item_id DESC ",
        resultSetMapping = "chatListMapping"
)
@SqlResultSetMapping(
        name = "chatListMapping",
        classes = @ConstructorResult(
                targetClass = ChatListResponseDTO.class,
                columns = {
                        @ColumnResult(name = "item_id", type = Integer.class),
                        @ColumnResult(name = "chat_id", type = Integer.class),
                        @ColumnResult(name = "opponentIndex", type = Integer.class),
                        @ColumnResult(name = "file_name", type = String.class),
                        @ColumnResult(name = "nickname", type = String.class)
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