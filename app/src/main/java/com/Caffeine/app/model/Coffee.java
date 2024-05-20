package com.Caffeine.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Coffee {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private BigDecimal coffeePrice = BigDecimal.valueOf(0.00);

    private Date createdAt = new Date();

    @ManyToMany()
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void countCoffeePrice(List<Ingredient> ingredients) {
        for(Ingredient ingredient : ingredients) {
            BigDecimal ingredientPrice = BigDecimal.valueOf(ingredient.getPrice());
            coffeePrice = coffeePrice.add(ingredientPrice);
        }
        coffeePrice = coffeePrice.setScale(2);
    }


}
