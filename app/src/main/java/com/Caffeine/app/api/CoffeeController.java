package com.Caffeine.app.api;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.repositories.CoffeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/coffees", produces = "application/json")
@CrossOrigin(origins={"http://caffeine:8080", "http://caffeine.com"})
public class CoffeeController {

    private CoffeeRepository coffeeRepository;

    public CoffeeController (CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @GetMapping(params="recent")
    public Iterable<Coffee> recentCoffees() {
        PageRequest page = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return coffeeRepository.findAll(page);
    }



}
