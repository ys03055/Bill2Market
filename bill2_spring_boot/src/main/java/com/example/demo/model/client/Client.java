package com.example.demo.model.client;

import lombok.*;

import javax.persistence.*;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="client_test")
public class Client {
    // 데이터베이스에 존재하는 client 테이블의 컬럼을 확인하지 못해서 일단 test용 테이블로 진행하였습니다.
    @Id
    private String clientId; //리뷰시간에 검토 -> 성능문제?
    @Column(nullable = false)
    private String password;
    private String name;
    private String nickname;
    private String address;
    private String email;
    private String phonenumber;
    private int age;
    private int trustpoint;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
