package com.Caffeine.app.adminApi;

import com.Caffeine.app.model.CoffeeOrder;
import com.Caffeine.app.repositories.OrderRepository;
import com.Caffeine.app.services.JmsOrderMessagingService;
import com.Caffeine.app.services.OrderMessagingService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("apiOrderController")
@RequestMapping(path="/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://caffeineapp:8080")
public class OrderRestController {

    private OrderRepository orderRepository;
    private OrderMessagingService messagingService;

    public OrderRestController(OrderRepository orderRepository, OrderMessagingService messagingService) {
        this.orderRepository = orderRepository;
        this.messagingService = messagingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoffeeOrder> getOrderById(@PathVariable("id") Long id) {
        Optional<CoffeeOrder> optionalCoffeeOrder = orderRepository.findById(id);
        if(optionalCoffeeOrder.isPresent()) {
            return new ResponseEntity<>(optionalCoffeeOrder.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PatchMapping(path="/{id}", consumes = "application/json")
    public CoffeeOrder patchOrder(@PathVariable("id") Long id, @RequestBody CoffeeOrder patch) {
        CoffeeOrder order = orderRepository.findById(id).get();
        if(patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if(patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if(patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if(patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if(patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if(patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        if(patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        CoffeeOrder orderToDelete = orderRepository.findById(id).get();
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder postOrder(@RequestBody CoffeeOrder order) {
        messagingService.sendOrder(order);
        return orderRepository.save(order);
    }


}
