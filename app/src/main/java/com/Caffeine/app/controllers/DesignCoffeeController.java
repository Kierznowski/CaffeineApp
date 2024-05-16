package com.Caffeine.app.controllers;

import com.Caffeine.app.model.Coffee;
import com.Caffeine.app.model.CoffeeOrder;
import com.Caffeine.app.model.Ingredient;
import com.Caffeine.app.model.Ingredient.Type;
import com.Caffeine.app.repositories.CoffeeRepository;
import com.Caffeine.app.repositories.IngredientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Matcher;

@Controller
@RequestMapping("/design")
@SessionAttributes("coffeeOrder")
public class DesignCoffeeController {

    private final IngredientRepository ingredientRepository;
    private final CoffeeRepository coffeeRepository;

    public DesignCoffeeController(IngredientRepository ingredientRepository, CoffeeRepository coffeeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.coffeeRepository = coffeeRepository;
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
    public String createCoffee(@Valid Coffee coffee, BindingResult bindingResult,
                               @ModelAttribute CoffeeOrder coffeeOrder) {

        long beanSelected = coffee.getIngredients().stream()
                                                        .filter(ingredient -> ingredient.getType().equals(Type.BEAN))
                                                        .count();
        String name = coffee.getName();

        if(beanSelected != 1) {
            bindingResult.rejectValue("ingredients", "error.ingredients",
                            "Please select one type of Coffee Beans");
        }

        long volumeSelected = coffee.getIngredients().stream()
                .filter(ingredient -> ingredient.getType().equals(Type.VOLUME))
                .count();

        if(volumeSelected == 0) {
            bindingResult.rejectValue("ingredients", "error.ingredients",
                    "Please select coffee volume");
        } else if(volumeSelected > 1) {
            bindingResult.rejectValue("ingredients", "error.ingredients",
                    "Please select only one coffee volume");
        }
        if(!name.matches("^[a-zA-Z0-9-_ ]*$")) {
            bindingResult.rejectValue("ingredients", "error.name",
                    "The name can only contain alphanumeric characters " +
                            "as well as spaces( ), underscores(_) and dashes(-)");
        }

        if (bindingResult.hasErrors()) {
            return "design";
        }

        coffeeOrder.addCoffee(coffee);

        return "redirect:/orders/current";
    }

    @GetMapping("/delete/{name}")
    public String deleteCoffeeFromOrder(@PathVariable("name") String name, @ModelAttribute CoffeeOrder order) {

        order.getCoffees().removeIf(coffee -> coffee.getName().equals(name));

        if(order.getCoffees().size() == 0) {
            return "redirect:/design";
        }

        return "redirect:/orders/current";
    }


    private Iterable<Ingredient> filterByType (List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
