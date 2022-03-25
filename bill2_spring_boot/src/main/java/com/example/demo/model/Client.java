package com.example.demo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table(name="Client")
public class Client {

    @Id
    private String client_id;
    @Column(nullable = false)
    private int age;
    private int trustpoint;
    private String password;
    private String name;
    private String nick_name;
    private String address;
    private String phone_number;
    private String email;
