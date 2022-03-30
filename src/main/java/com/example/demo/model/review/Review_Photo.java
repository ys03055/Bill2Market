package com.example.demo.model.review;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Review_Photo")
public class Review_Photo {
    @Id
    private int contract_id;

    @Column(nullable = false)
    private String review_photo;
}
