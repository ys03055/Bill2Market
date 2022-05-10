package com.example.demo.model.client;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.sql.Date;


import com.example.demo.model.item.OwnerInfo;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="Client")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "ownerInfo",
                query = "SELECT nickname, ROUND(AVG(Review.review_score),1) AS trust_point " +
                        "FROM Client LEFT JOIN Review " +
                        "ON Client.client_index = Review.review_target AND Review.review_type IN(0, 1) " +
                        "WHERE Client.client_index = :client_index",
                resultSetMapping = "ownerInfoMapping"
        ),
        @NamedNativeQuery(
                name = "reviewPointCalculator",
                query = "SELECT ROUND(AVG(Review.review_score),1) AS trust_point " +
                        "FROM Client LEFT JOIN Review " +
                        "ON Client.client_index = Review.review_target AND Review.review_type IN(0, 1) " +
                        "WHERE Client.client_index = :client_index",
                resultSetMapping = "ClientTrustPointResponseDTOMapping"

        )
})
@SqlResultSetMapping(
        name = "ownerInfoMapping",
        classes = @ConstructorResult(
                targetClass = OwnerInfo.class,
                columns = {
                        @ColumnResult(name = "nickname", type = String.class),
                        @ColumnResult(name = "trust_point", type = Float.class)
                }
        )
)
@SqlResultSetMapping(
        name = "ClientTrustPointResponseDTOMapping",
        classes = @ConstructorResult(
                targetClass = ClientTrustPointResponseDTO.class,
                columns = {
                        @ColumnResult(name = "trust_point", type = Float.class)
                }
        )
)
public class Client {
    @Id
    @Column(name = "client_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientIndex;
    @Column(name = "client_id")
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
    @Enumerated(EnumType.ORDINAL)
    private SnsType snsType;
    @Column(name = "client_name")
    private String clientName;
    @Column
    private String nickname;
    @Column
    private int subscribe;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Role role;
}