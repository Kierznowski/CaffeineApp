package com.Caffeine.app.services;

import com.Caffeine.app.model.CoffeeOrder;

public interface OrderMessagingService {
    void sendOrder(CoffeeOrder order);
}
