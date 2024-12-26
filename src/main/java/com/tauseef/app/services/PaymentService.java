package com.tauseef.app.services;

import com.tauseef.app.contracts.PaymentInterface;
import com.tauseef.app.models.Order;

public class PaymentService {

    private PaymentInterface payment;

    public void setPaymentGateway(PaymentInterface payment) {
        this.payment = payment;
    }

    public void pay(Order order) {
        payment.pay(order);
    }
}
