package com.tauseef.app.states;

import com.tauseef.app.contracts.OrderStateInterface;

public class OrderPreparing implements OrderStateInterface {

    @Override
    public String getState() {
        return "Order Preparing";
    }
}
