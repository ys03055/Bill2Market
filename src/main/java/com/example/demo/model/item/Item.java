package com.example.demo.model.item;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;




@Getter

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Item")

@NamedNativeQuery(
    name = "SimpleItemSliceByLocation",
    query = "SELECT item.item_id, item_title, item_address, price, deposit, p1.item_photo, item_status, create_date, " +
            "EXISTS(SELECT item_id FROM basket WHERE basket.item_id = item.item_id AND basket.client_index = 1) AS is_basket " +
            "FROM item " +
            "LEFT JOIN item_photo AS p1 on item.item_id = p1.item_id " +
            "LEFT JOIN item_photo AS p2 ON p1.item_id = p2.item_id " +
            "AND p1.photo_index > p2.photo_index " +
            "WHERE p2.photo_index IS NULL " +
            "AND ST_DISTANCE_SPHERE( POINT(:client_longitude, :client_latitude), POINT(item_longitude, item_latitude)) <= 1000 " +
            "ORDER BY ST_Distance_Sphere( POINT(:client_longitude, :client_latitude), POINT(item_longitude, item_latitude))",
    resultSetMapping = "SimpleItemMapping"
)
@SqlResultSetMapping(
        name = "SimpleItemMapping",
        classes = @ConstructorResult(
                targetClass = SimpleItem.class,
                columns = {
                        @ColumnResult(name = "item_id", type = Integer.class),
                        @ColumnResult(name = "item_title", type = String.class),
                        @ColumnResult(name = "item_address", type = String.class),
                        @ColumnResult(name = "price", type = Integer.class),
                        @ColumnResult(name = "deposit", type = Integer.class),
                        @ColumnResult(name = "item_photo", type = String.class),
                        @ColumnResult(name = "item_status", type = ItemStatus.class),
                        @ColumnResult(name = "create_date", type = java.util.Date.class),
                        @ColumnResult(name = "is_basket", type = Boolean.class)
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
    @Enumerated(EnumType.STRING)
    @Column(name = "contract_status")
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
