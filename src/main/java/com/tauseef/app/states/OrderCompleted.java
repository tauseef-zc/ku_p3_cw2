package com.tauseef.app.states;

import com.tauseef.app.contracts.OrderStateInterface;

public class OrderCompleted implements OrderStateInterface {

    @Override
    public String getState() {
        return "Order Completed";
    }
}
