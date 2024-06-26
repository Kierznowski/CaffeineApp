package com.caffeine.adminApp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;
    private double price;

    public enum Type {
        BEAN, MILK, SWEETENER, EXTRA, VOLUME
    }

}
