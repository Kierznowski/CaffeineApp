package com.caffeine.barista.messaging;

import com.caffeine.barista.model.CoffeeOrder;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Profile("jms-listener")
@Component
public class OrderListener {

    @JmsListener(destination = "caffeine.order.queue")
    public void receiveOrder(CoffeeOrder order) {

    }
}
