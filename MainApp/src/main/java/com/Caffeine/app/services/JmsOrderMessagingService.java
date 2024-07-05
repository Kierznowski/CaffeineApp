package com.Caffeine.app.services;

import com.Caffeine.app.model.CoffeeOrder;
import jakarta.jms.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private final JmsTemplate jms;
    private final Destination orderQueue;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue) {
        this.jms = jms;
        this.orderQueue = orderQueue;

    }
    @Override
    public void sendOrder(CoffeeOrder order) {
        jms.convertAndSend(String.valueOf(orderQueue), order);
    }

}
