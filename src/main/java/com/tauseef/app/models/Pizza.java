package com.tauseef.app.models;

import com.tauseef.app.builders.PizzaBuilder;
import com.tauseef.app.enums.SizeEnum;

import java.util.*;

abstract class Pizza {

    protected SizeEnum crust;
    protected final boolean sauce;
    protected final List<String> toppings;
    protected final List<SizeEnum> cheese;

    public Pizza(PizzaBuilder builder) {
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        this.cheese = builder.cheese;
    }

    public SizeEnum getCrust() {
        return crust;
    }

    public void setCrust(SizeEnum crust) {
        this.crust = crust;
    }

    public void addToppings(String topping) {
        this.toppings.add(topping);
    }

    public List<String> getToppings() {
        return this.toppings;
    }

    public void addCheese(SizeEnum cheese) {
        this.cheese.add(cheese);
    }

    public List<SizeEnum> getCheese() {
        return this.cheese;
    }

    @Override
    public String toString() {
        return "{" +
                "crust='" + crust + '\'' +
                ", sauce='" + sauce + '\'' +
                ", toppings=" + toppings +
                ", cheese='" + cheese + '\'' +
                '}';
    }

}
