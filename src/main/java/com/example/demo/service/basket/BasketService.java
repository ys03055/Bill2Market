package com.example.demo.service.basket;

import com.example.demo.model.basket.Basket;

public interface BasketService {

    public long countBasketByItem(int itemId);
    public Basket saveBasket(int clientIndex, int itemId);
    public void deleteBasket(int clientIndex, int itemId);

}
