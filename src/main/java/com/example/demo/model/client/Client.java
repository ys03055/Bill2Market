package com.example.demo.model.client;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_index;
    @Column(nullable = false, name = "client_id")
    private String clientId;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private Date birthdate;
    @Column(name = "sns_type")
    private int snsType;
    @Column(name = "client_name")
    private String clientName;
    @Column
    private String nickname;
    @Column(name = "client_address")
    private String clientAddress;
    @Column(name = "client_longitude")
    private double clientLongitude;
    @Column(name = "client_latitude")
    private double clientLatitude;
    @Column
    private int subscribe;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
