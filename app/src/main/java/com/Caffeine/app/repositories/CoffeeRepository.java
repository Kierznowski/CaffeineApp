package com.Caffeine.app.repositories;

import com.Caffeine.app.model.Coffee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CoffeeRepository extends
    PagingAndSortingRepository <Coffee, Long> {
    
    Optional<Coffee> findById(Long id);
    Optional<Coffee> findByName(String name);

    Coffee save(Coffee coffee);
}
