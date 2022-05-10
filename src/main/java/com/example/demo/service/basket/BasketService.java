package com.example.demo.service.basket;

import com.example.demo.model.basket.Basket;
import com.example.demo.model.basket.BasketMyListResponseDTO;

import java.util.List;

public interface BasketService {

    public long countBasketByItem(int itemId);
    public Basket saveBasket(int clientIndex, int itemId);
    public void deleteBasket(int clientIndex, int itemId);
    public List<BasketMyListResponseDTO> findMyBasketList(Integer ownerId);
}
