package com.example.demo.model.item;

import lombok.*;
import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Item")
public class Item {
    @Id
    private int item_id;
    @Column(nullable = false)
    private int owner_index;
    private String category_big;
    private String category_middle;
    private String category_small;
    private String item_title;
    private String item_content;
    private String item_status;
    private String contract_status;
    private String item_address;
    private Date start_date;
    private Date end_date;
    private Date create_date;
    private Date update_date;
    private int price;
    private int deposit;
    private int views;
    private Point item_location;
}
