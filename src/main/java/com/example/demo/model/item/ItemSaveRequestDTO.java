package com.example.demo.model.item;

import lombok.Data;
import org.springframework.data.geo.Point;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ItemSaveRequestDTO {

    private Integer categoryBig;
    private Integer categoryMiddle;
    private Integer categorySmall;
    private String itemTitle;
    private String itemContent;
    private ItemQuality itemQuality;
    private int price;
    private int deposit;
    private String itemAddress;
    private double itemLatitude;
    private double itemLongitude;
    private Integer ownerId;
    private String startDate;
    private String endDate;
    private ContractStatus contractStatus;


    public Item toEntity(Point point) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatter.parse(getStartDate());
            endDate = formatter.parse(getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Item.builder()
                .itemLongitude(point.getX())
                .itemLatitude(point.getY())
                .ownerId(ownerId)
                .itemTitle(itemTitle)
                .categoryBig(categoryBig)
                .categoryMiddle(categoryMiddle)
                .categorySmall(categorySmall)
                .itemContent(itemContent)
                .itemQuality(itemQuality)
                .price(price)
                .deposit(deposit)
                .itemAddress(itemAddress)
                .views(0)
                .contractStatus(contractStatus)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
