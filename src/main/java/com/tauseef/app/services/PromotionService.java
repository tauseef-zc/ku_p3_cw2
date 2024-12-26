package com.tauseef.app.services;

import com.tauseef.app.models.Order;
import java.time.LocalDateTime;

public abstract class PromotionService {

    protected final LocalDateTime today = LocalDateTime.now();
    protected PromotionService nextHandler;

    public void setNextHandler(PromotionService nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void applyDiscount(Order order);

    public abstract double getDiscountPercentage();

    public abstract String getPromotionName();

    public void updateOrder(Order order) {
        double orderTotal = order.getGrandTotal();
        double calculateDiscount = (orderTotal * getDiscountPercentage()) / 100;
        double newTotal = orderTotal - calculateDiscount;
        order.setDiscount(calculateDiscount);
        order.setFullTotal(newTotal);
        order.addPromotion(this);
    }
}
