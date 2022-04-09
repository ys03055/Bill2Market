package com.example.demo.model.item;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Item")
@NamedNativeQuery(
        name = "SimpleItemSliceByLocation",
        query = "SELECT Item.item_id, Item_Photo.item_photo_index, item_title, price, deposit, item_address, Item_Photo.item_photo, contract_status, create_date, " +
                "IF(Basket.item_id = Item.item_id, TRUE, FALSE) AS is_like " +
                "FROM Item LEFT JOIN Item_Photo " +
                "ON Item.item_id = Item_Photo.item_id " +
                "AND Item_Photo.is_main = 1 " +
                "LEFT JOIN Basket ON Basket.client_index = :client_index " +
                "WHERE ST_Distance_Sphere(POINT(:client_longitude, :client_latitude), POINT(item_longitude, item_latitude)) <= 10000 " +
                "GROUP BY item_id ORDER BY ST_Distance_Sphere(POINT(:client_longitude, :client_latitude), POINT(item_longitude, item_latitude))",
        resultSetMapping = "SimpleItemMapping"
)
@SqlResultSetMapping(
        name = "SimpleItemMapping",
        classes = @ConstructorResult(
                targetClass = SimpleItem.class,
                columns = {
                        @ColumnResult(name = "item_id", type = Integer.class),
                        @ColumnResult(name = "item_photo_index", type = Integer.class),
                        @ColumnResult(name = "item_title", type = String.class),
                        @ColumnResult(name = "item_address", type = String.class),
                        @ColumnResult(name = "price", type = Integer.class),
                        @ColumnResult(name = "deposit", type = Integer.class),
                        @ColumnResult(name = "item_photo", type = String.class),
                        @ColumnResult(name = "contract_status", type = String.class),
                        @ColumnResult(name = "create_date", type = java.util.Date.class),
                        @ColumnResult(name = "is_like", type = Boolean.class)
                }
        )
)
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column(name = "owner_id")
    private Integer ownerId;
    @Column(name = "category_big")
    private String categoryBig;
    @Column(name = "category_middle")
    private String categoryMiddle;
    @Column(name = "category_small")
    private String categorySmall;
    @Column(name = "item_title")
    private String itemTitle;
    @Column(name = "item_content")
    private String itemContent;
    @Column(name = "item_quality")
    private String itemQuality;
    @Column(name = "contract_status")
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "update_date")
    private Date updateDate;
    @Column
    private int price;
    @Column
    private int deposit;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "item_latitude")
    private double itemLatitude;
    @Column(name = "item_longitude")
    private double itemLongitude;
    @Column(name = "item_address")
    private String itemAddress;
    @Column
    private int views;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "item_id")
    @Builder.Default
    private List<ItemPhoto> photos = new ArrayList<ItemPhoto>();

}

}
