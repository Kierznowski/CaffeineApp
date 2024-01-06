package com.Caffeine.app.controllers;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.model.CoffeeOrder;
import com.Caffeine.app.model.Ingredient;
import com.Caffeine.app.model.Ingredient.Type;
import com.Caffeine.app.repositories.IngredientRepository;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("coffeeOrder")
public class DesignCoffeeController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignCoffeeController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel (Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "coffeeOrder")
    public CoffeeOrder order() {
        return new CoffeeOrder();
    }

    @ModelAttribute(name = "coffee")
    public Coffee coffee() {
        return new Coffee();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String createCoffee(@Valid Coffee coffee, Errors errors,
                               @ModelAttribute CoffeeOrder coffeeOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        coffeeOrder.addCoffee(coffee);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType (List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
