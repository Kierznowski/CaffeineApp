package com.caffeine.barista.services;

import com.caffeine.barista.model.CoffeeOrder;
import jakarta.jms.JMSException;

public interface OrderReceiver {
    CoffeeOrder receiveOrder() throws JMSException;
}
