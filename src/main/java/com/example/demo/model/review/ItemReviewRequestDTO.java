package com.example.demo.model.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class ItemReviewRequestDTO {

    private Integer itemId;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewScore;

}
