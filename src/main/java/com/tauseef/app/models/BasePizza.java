package com.tauseef.app.models;

import com.tauseef.app.builders.PizzaBuilder;
import com.tauseef.app.enums.SizeEnum;

import java.util.HashMap;
import java.util.Map;

public class BasePizza extends Pizza {
    private static int count = 1;
    protected final String name;
    protected final double basePrice;
    protected final Map<SizeEnum, Double> priceList = new HashMap<>();
    private final int id;

    public BasePizza(PizzaBuilder builder) {
        super(builder);
        this.id = count++;
        this.name = builder.getName();
        this.basePrice = builder.getBasePrice();
        loadPrices();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "{ ID: " + id + " " + super.toString() + " }";
    }

    public double getPrice() {
        return priceList.get(this.getCrust());
    }

    protected void loadPrices() {
        priceList.put(SizeEnum.SMALL, basePrice * 1);
        priceList.put(SizeEnum.MEDIUM, basePrice * 1.5);
        priceList.put(SizeEnum.LARGE, basePrice * 2);
    }
}
