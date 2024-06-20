package com.Caffeine.app.adminApi;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.repositories.CoffeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("apiCoffeeController")
@RequestMapping(path="/api/coffees", produces="application/json")
@CrossOrigin(origins="http://caffeineapp:8080")
public class CoffeeController {

    private CoffeeRepository coffeeRepository;

    public CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @GetMapping(params="recent")
    public Iterable<Coffee> recentCoffees() {
        PageRequest page = PageRequest.of(0, 10,
                Sort.by("createdAt").descending());
        return coffeeRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> coffeeById(@PathVariable("id") Long id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if(optionalCoffee.isPresent()) {
            return new ResponseEntity<>(optionalCoffee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Coffee postCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

}
