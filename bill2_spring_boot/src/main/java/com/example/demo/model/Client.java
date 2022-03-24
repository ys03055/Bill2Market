package com.example.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "client")
public class Client {

    @Id
    private Long clientPk;
    @Column
    private String id;
    @Column
    private String password;
    @Column
    private String nickName;

}
