package com.example.demo.repository;

import com.example.demo.model.client.Client;
import com.example.demo.model.item.OwnerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Optional<Client> findByClientId (@Param("clientId") String clientId);
    public Client findByPhoneNumber(String phone_number);
    public Optional<Client> findByNickname(String nickname);

    @Query(name = "ownerInfo", nativeQuery = true)
    public Optional<OwnerInfo> findOwnerInfoByClientIndex(@Param("client_index") Integer clientIndex);

    @Query(value = "SELECT ROUND(AVG(Review.review_score),1) AS trustPoint " +
            "FROM Client LEFT JOIN Review " +
            "ON Client.client_index = Review.review_target AND Review.review_type IN(0, 1) " +
            "WHERE Client.client_index = :client_index", nativeQuery = true)
    Float findReviewPointByClientIndex(@Param("client_index") Integer clientIndex);

}
