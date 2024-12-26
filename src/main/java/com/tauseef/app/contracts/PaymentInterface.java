package com.tauseef.app.contracts;

import com.tauseef.app.models.Order;

public interface PaymentInterface {

    String getName();

    void pay(Order order);

}