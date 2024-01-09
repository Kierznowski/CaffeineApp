package com.Caffeine.app.repositories;

import com.Caffeine.app.model.Coffee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoffeeRepository extends
    PagingAndSortingRepository <Coffee, Long> {
}
