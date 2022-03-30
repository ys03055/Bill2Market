package com.example.demo.model.basket;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Basket")
public class Basket {
    @Id
    private int client_index;
    @Id
    private int item_id;
}
