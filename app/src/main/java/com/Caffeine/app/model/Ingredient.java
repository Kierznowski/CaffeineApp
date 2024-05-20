package com.Caffeine.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;
    private double price;

    public enum Type {
        BEAN, MILK, SWEETENER, EXTRA, VOLUME
    }
}
