package com.tauseef.app.states;

import com.tauseef.app.contracts.OrderStateInterface;

public class OrderCancelled  implements OrderStateInterface {

    @Override
    public String getState() {
        return "Order Cancelled";
    }
}
