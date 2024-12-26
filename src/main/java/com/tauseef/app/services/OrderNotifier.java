package com.tauseef.app.services;

import com.tauseef.app.contracts.OrderObserver;
import com.tauseef.app.models.Order;
import java.util.HashMap;
import java.util.Map;

public class OrderNotifier {

    private final Map<Order, OrderObserver> observers = new HashMap<>();

    public void addObserver(Order order, OrderObserver observer) {
        observers.put(order, observer);
    }

    public void notifyObservers() {
        observers.forEach((order, observer) -> observer.notify(order.getOrderStatus()));
    }
}