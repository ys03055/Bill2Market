package com.example.demo.model.chat;

import com.example.demo.model.client.Client;
import com.example.demo.model.item.BaseEntity;
import com.example.demo.model.item.Item;
import lombok.*;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Chat")
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;
    @OneToOne
    @JoinColumn(name = "lenter_index")
    private Client lenter;
    @OneToOne
    @JoinColumn(name = "owner_index")
    private Client owner;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "file_name")
    private String fileName;

}
