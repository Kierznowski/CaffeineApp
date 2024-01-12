package com.Caffeine.app.api;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.repositories.CoffeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/name/{name}")
    public ResponseEntity<Coffee> coffeeByName(@PathVariable("name") String name) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findByName(name);
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


    @PutMapping(path = "/{id}", consumes = "application/json")
    public Coffee putCoffee(@PathVariable("id") Long id, @RequestBody Coffee coffee) {
        coffee.setId(id);
        return coffeeRepository.save(coffee);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Coffee patchCoffee(@PathVariable("id") Long id, @RequestBody Coffee patch) {
        Coffee coffee = coffeeRepository.findById(id).get();
        if(patch.getName() != null) {
            coffee.setName(patch.getName());
        }
        if (patch.getIngredients() != null) {
            coffee.setIngredients(patch.getIngredients());
        }
        return coffeeRepository.save(coffee);
    }

    @PatchMapping(path = "/{name}", consumes = "application/json")
    public Coffee patchCoffee(@PathVariable("name") String name, @RequestBody Coffee patch) {
        Coffee coffee = coffeeRepository.findByName(name).get();
        coffee.setIngredients(patch.getIngredients());
        return coffeeRepository.save(coffee);
    }

}
