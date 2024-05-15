package com.Caffeine.app.repositories;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<CoffeeOrder, Long> {

}
