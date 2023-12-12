package com.Caffeine.app.repositories;

import com.Caffeine.app.model.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}