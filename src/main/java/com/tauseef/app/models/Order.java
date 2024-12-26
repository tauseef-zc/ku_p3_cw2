package com.tauseef.app.models;

import com.tauseef.app.contracts.FeedbackInterface;
import com.tauseef.app.contracts.OrderStateInterface;
import com.tauseef.app.contracts.PaymentInterface;
import com.tauseef.app.services.PromotionService;
import com.tauseef.app.services.RatingService;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private static int count = 1;
    private final int id;
    private final Customer customer;
    private final List<BasePizza> pizzas;
    private double total;
    private double discount;
    private double redeemPoints = 0;
    private PromotionService promotion;
    private PaymentInterface paymentGateway;
    private OrderStateInterface orderStatus;
    private FeedbackInterface feedback;
    private LocalDateTime orderDate;

    public Order(Customer customer, List<BasePizza> pizzas) {
        this.customer = customer;
        this.pizzas = pizzas;
        this.id = count++;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public List<BasePizza> getOrderItems() {
        return pizzas;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getGrandTotal() {
        double total = 0;
        for (BasePizza pizza : pizzas) {
            total += pizza.getPrice();
        }
        return total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double calculateDiscount) {
        this.discount = calculateDiscount;
    }

    public double getFullTotal() {
        return total;
    }

    public void setFullTotal(double newTotal) {
        this.total = newTotal;
    }

    public PaymentInterface getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(PaymentInterface paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void addPromotion(PromotionService promotion) {
        this.promotion = promotion;
    }

    public String getOrderStatus() {
        return orderStatus.getState();
    }

    public void setOrderStatus(OrderStateInterface orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void addFeedback(FeedbackInterface feedback) {
        this.feedback = feedback;
    }

    public void redeemLoyaltyPoints(double loyaltyPoints) {
        redeemPoints = loyaltyPoints;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime now) {
        this.orderDate = now;
    }

    public RatingService getFeedback() {
        return (RatingService) feedback;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", pizzas=" + pizzas +
                ", total=" + total +
                ", discount=" + discount +
                ", redeemPoints=" + redeemPoints +
                ", promotion=" + promotion.getPromotionName() +
                ", paymentGateway=" + paymentGateway.getClass().getSimpleName() +
                '}';
    }

    public PromotionService getPromotion() {
        return promotion;
    }

    public double getRedeemedPoints() {
        return redeemPoints;
    }
}
