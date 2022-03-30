package com.example.demo.model.review;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Review")

public class Review {
    @Id
    private int contract_id;

    @Column(nullable = false)
    private Date create_date;
    private int review_score;
    private String type;
    private String ireview_title;
    private String ireview_content;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private String review_status;
}
