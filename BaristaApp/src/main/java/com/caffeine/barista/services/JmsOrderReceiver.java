package com.caffeine.barista.services;

import com.caffeine.barista.model.CoffeeOrder;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public CoffeeOrder receiveOrder() throws JMSException {
        return (CoffeeOrder) jms.receiveAndConvert("caffeine.order.queue");
    }

}
