package com.caffeine.barista.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Coffee {
    private String name;
    private Date createdAt;
    private List<Ingredient> ingredients;


}
