package com.tauseef.app.builders;

import com.tauseef.app.enums.SizeEnum;
import com.tauseef.app.models.BasePizza;
import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {

    public final List<String> toppings = new ArrayList<>();
    public final List<SizeEnum> cheese = new ArrayList<>();
    public boolean sauce;
    private String name;
    private double basePrice;

    public String getName() {
        return name;
    }

    public PizzaBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public PizzaBuilder setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public PizzaBuilder addSauce(boolean sauce) {
        this.sauce = sauce;
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        this.toppings.add(topping);
        return this;
    }

    public PizzaBuilder addCheese(SizeEnum cheese) {
        this.cheese.add(cheese);
        return this;
    }

    public BasePizza build() {
        return new BasePizza(this);
    }

}