package com.caffeine.barista.model;

import lombok.Data;

@Data
public class Ingredient {
    private final String name;
    private final Type type;
    private double price;

    public enum Type {
        BEAN, MILK, SWEETENER, EXTRA, VOLUME
    }
}
