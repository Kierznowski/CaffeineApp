package com.Caffeine.app.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import java.util.List;

@Data
@UserDefinedType("Coffee")
public class CoffeeUDT {

    private final String name;

    private final List<IngredientUDT> ingredients;
}
