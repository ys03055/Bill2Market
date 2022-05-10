package com.example.demo.repository;

import com.example.demo.model.basket.Basket;
import com.example.demo.model.basket.BasketMyListResponseDTO;
import com.example.demo.model.basket.BasketPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, BasketPK> {
    @Query(value = "SELECT COUNT(client_index) FROM Basket WHERE item_id = :item_id", nativeQuery = true)
    public long countByItemId(@Param("item_id") Integer itemId);

    @Query(value = "SELECT EXISTS(SELECT item_id FROM Basket WHERE item_id = :item_id AND client_index = :client_index)", nativeQuery = true)
    public int existsBasketByBasketPK(@Param("item_id") Integer itemId, @Param("client_index") Integer clientIndex);

    @Query(name = "BasketsMeByClientIndex", nativeQuery = true)
    public List<BasketMyListResponseDTO> findBasketListByClientIndex(@Param("owner_id") Integer ownerId);
}
