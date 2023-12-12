package com.Caffeine.app.repositories;

import com.Caffeine.app.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<CoffeeOrder, UUID> {

}
