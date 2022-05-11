package com.example.demo.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.util.Date;

@AllArgsConstructor
@Getter
@Data
public class ClientTrustPointResponseDTO {

    private Integer clientIndex;
    private String clientId;
    private String password;
    private String email;
    private String phoneNumber;
    private Date birthdate;
    private String snsType;
    private String clientName;
    private String nickname;
    private Integer subscribe;
    private String role;
    private Float trustPoint;
}
