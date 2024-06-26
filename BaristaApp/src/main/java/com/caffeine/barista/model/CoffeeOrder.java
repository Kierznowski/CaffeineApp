package com.caffeine.barista.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CoffeeOrder {

    private Date placedAt;
    private String orderName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryZip;

    private List<Coffee> coffees = new ArrayList<>();



}
