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
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findLenterInfo",
                query = "SELECT DISTINCT Contract.contract_id, Contract.deposit, Contract.price, Chat.lenter_index, Chat.owner_index, Client.client_name, Billy_Pay.fintech_id " +
                        "FROM Contract " +
                        "INNER JOIN Chat ON Contract.chat_id = Chat.chat_id " +
                        "INNER JOIN Client ON Chat.lenter_index=Client.client_index " +
                        "LEFT JOIN Billy_Pay ON Client.client_index = Billy_Pay.client_index " +
                        "WHERE :contract_id=Contract.contract_id ",
                resultSetMapping = "DepositForClientDTOMapping"
        ),
        @NamedNativeQuery(
                name = "ContractIBorrowedByClientIndex",
                query="SELECT Item.item_id, Item.item_title, Item.price, Item.deposit, Item.item_address, Item_Photo.item_photo, Contract.contract_status, Contract.start_date " +
                        "FROM Contract LEFT JOIN Chat ON Contract.chat_id=Chat.chat_id LEFT JOIN Item ON Chat.item_id = Item.item_id LEFT JOIN Item_Photo ON Item.item_id = Item_Photo.item_id " +
                        "WHERE Chat.lenter_index = :client_index AND is_main=1 " +
                        "ORDER BY Contract.contract_status,Contract.start_date DESC ",
                resultSetMapping = "ItemIBorrowedMapping"
        )
})
@SqlResultSetMapping(
        name = "DepositForClientDTOMapping",
        classes = @ConstructorResult(
                targetClass = DepositForClientDTO.class,
                columns = {
                        @ColumnResult(name = "contract_id", type = Integer.class),
                        @ColumnResult(name = "deposit", type = Integer.class),
                        @ColumnResult(name = "price", type = Integer.class),
                        @ColumnResult(name = "lenter_index", type = Integer.class),
                        @ColumnResult(name = "owner_index", type = Integer.class),
                        @ColumnResult(name = "client_name", type = String.class),
                        @ColumnResult(name = "fintech_id", type = String.class),
                }
        )
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
                        @ColumnResult(name = "start_date", type = LocalDate.class)
                }
        )
)
public class Contract {
    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;
    @OneToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @Column(name = "contract_status")
    @Enumerated(value = EnumType.ORDINAL)
    private ContractType contractStatus;
    @Column(name = "price")
    private Integer price;
    @Column(name = "deposit")
    private Integer deposit;
    @Column(name = "billpay_status")
    @Enumerated(value = EnumType.ORDINAL)
    private BillpayStatus billpayStatus;
    @Column(name = "permission_status")
    @Enumerated(value = EnumType.ORDINAL)
    private PermissionStatus permissionStatus;
    @Column(name = "retrieve_status")
    @Enumerated(value = EnumType.ORDINAL)
    private RetrieveStatus retrieveStatus;
    @Column(name = "contract_date")
    private LocalDate contractDate;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "review_write")
    @Enumerated(value = EnumType.ORDINAL)
    private ReviewWrite reviewWrite;

}
