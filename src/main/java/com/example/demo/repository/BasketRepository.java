package com.example.demo.repository;

import com.example.demo.model.basket.Basket;
import com.example.demo.model.basket.BasketPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, BasketPK> {
    @Query(value = "SELECT COUNT(client_index) FROM basket WHERE item_id = :item_id", nativeQuery = true)
    public long countByItemId(@Param("item_id") int itemId);
}
