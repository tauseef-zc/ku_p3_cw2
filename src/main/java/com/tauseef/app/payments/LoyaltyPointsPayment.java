package com.tauseef.app.payments;

import com.tauseef.app.contracts.PaymentInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.models.Customer;
import com.tauseef.app.models.Order;

public class LoyaltyPointsPayment implements PaymentInterface {

    final Console console = Console.getInstance();

    @Override
    public String getName() {
        return "Loyalty Points";
    }

    @Override
    public void pay(Order order) {

        Customer customer = order.getCustomer();
        if (customer.getLoyaltyPoints() < order.getGrandTotal()) {

            console.print("You don't have enough loyalty points to pay for this order.");

            if (customer.getLoyaltyPoints() > 0) {
                boolean redeem = console.askBoolean("Would you like to redeem your loyalty points (" + customer.getLoyaltyPoints() + ")?");
                if (redeem) {
                    order.redeemLoyaltyPoints(customer.getLoyaltyPoints());
                    customer.setLoyaltyPoints(0);
                }
            }

        } else {
            customer.setLoyaltyPoints(customer.getLoyaltyPoints() - order.getFullTotal());
            order.setFullTotal(order.getGrandTotal() - order.getDiscount());
            order.setPaymentGateway(this);
        }
    }
}