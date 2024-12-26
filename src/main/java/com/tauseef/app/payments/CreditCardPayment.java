package com.tauseef.app.payments;

import com.tauseef.app.contracts.PaymentInterface;
import com.tauseef.app.models.Order;

public class CreditCardPayment implements PaymentInterface {

    @Override
    public String getName() {
        return "Credit Card";
    }

    @Override
    public void pay(Order order) {
        order.setFullTotal(order.getGrandTotal() - order.getDiscount());
        order.setPaymentGateway(this);
    }

}