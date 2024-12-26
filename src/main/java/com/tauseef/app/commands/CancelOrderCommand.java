package com.tauseef.app.commands;

import com.tauseef.app.contracts.CommandInterface;
import com.tauseef.app.models.Order;
import com.tauseef.app.states.OrderCancelled;

public class CancelOrderCommand implements CommandInterface {

    private final Order order;

    public CancelOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.setOrderStatus(new OrderCancelled());
        System.out.println("Order has been canceled.");
    }
}
