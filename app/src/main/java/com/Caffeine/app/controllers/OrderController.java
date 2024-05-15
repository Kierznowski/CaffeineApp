package com.Caffeine.app.controllers;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.model.CoffeeOrder;
import com.Caffeine.app.model.User;
import com.Caffeine.app.repositories.CoffeeRepository;
import com.Caffeine.app.repositories.OrderRepository;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.logging.Logger;


@Controller
@RequestMapping("/orders")
@SessionAttributes("coffeeOrder")
public class OrderController {

    Logger log = Logger.getLogger("order");

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid CoffeeOrder order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {

        if(errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "orderConfirmation";
    }
}
