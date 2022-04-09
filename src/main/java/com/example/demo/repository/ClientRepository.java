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
    Optional<Client> findByClientId (@Param("clientId") String clientId);
    Client findByPhoneNumber(String phone_number);

    @Query(name = "ownerInfo", nativeQuery = true)
    public Optional<OwnerInfo> findOwnerInfoByClientIndex(@Param("client_index") Integer clientIndex);

}
