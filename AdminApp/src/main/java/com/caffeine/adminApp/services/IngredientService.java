package com.caffeine.adminApp.services;

import com.caffeine.adminApp.model.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);
}
