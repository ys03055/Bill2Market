package com.example.demo.model.contract;

import com.example.demo.model.chat.Chat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Contract")
@NamedNativeQuery(
        name = "ContractIBorrowedByClientIndex",
        query="SELECT Item.item_id, Item.item_title, Item.price, Item.deposit, Item.item_address, Item_Photo.item_photo, Contract.contract_status, Contract.start_date, Contract.review_write " +
                "FROM Contract LEFT JOIN Chat ON Contract.chat_id=Chat.chat_id LEFT JOIN Item ON Chat.item_id = Item.item_id LEFT JOIN Item_Photo ON Item.item_id = Item_Photo.item_id " +
                "WHERE Chat.lenter_index = :client_index AND is_main=1 " +
                "ORDER BY Contract.contract_status,Contract.start_date DESC ",
        resultSetMapping = "ItemIBorrowedMapping"

)
@SqlResultSetMapping(
        name = "ItemIBorrowedMapping",
        classes = @ConstructorResult(
                targetClass = ContractIBorrowedResponseDTO.class,
                columns = {
                        @ColumnResult(name = "item_id", type = Integer.class),
                        @ColumnResult(name = "item_title", type = String.class),
                        @ColumnResult(name = "price", type = Integer.class),
                        @ColumnResult(name = "deposit", type = Integer.class),
                        @ColumnResult(name = "item_address", type = String.class),
                        @ColumnResult(name = "item_photo", type = String.class),
                        @ColumnResult(name = "contract_status", type = String.class),
                        @ColumnResult(name = "start_date", type = LocalDate.class),
                        @ColumnResult(name = "review_write", type = Integer.class)
                }
        ))

public class Contract {
    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    @OneToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @Column(name = "contract_date")
    private LocalDate contractDate;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "contract_status")
    @Enumerated(value = EnumType.ORDINAL)
    private ContractType contractStatus;
    @Column(name = "review_write")
    private ReviewWrite reviewWrite;

}
