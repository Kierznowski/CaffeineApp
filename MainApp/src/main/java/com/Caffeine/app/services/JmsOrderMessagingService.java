package com.Caffeine.app.services;

import com.Caffeine.app.model.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private final JmsTemplate jms;
    private Destination orderQueue;

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
