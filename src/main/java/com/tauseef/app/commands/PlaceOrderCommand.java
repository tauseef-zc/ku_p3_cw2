package com.tauseef.app.commands;

import com.tauseef.app.contracts.CommandInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.models.Order;
import com.tauseef.app.payments.CashPayment;
import com.tauseef.app.payments.CreditCardPayment;
import com.tauseef.app.payments.LoyaltyPointsPayment;
import com.tauseef.app.promotions.NewYearPromotion;
import com.tauseef.app.promotions.YearEndPromotion;
import com.tauseef.app.services.OrderNotifier;
import com.tauseef.app.services.PaymentService;
import com.tauseef.app.services.PromotionService;
import com.tauseef.app.states.OrderPreparing;

public class PlaceOrderCommand implements CommandInterface {

    private final Order order;
    private final Console console;

    public PlaceOrderCommand(Order order) {
        this.order = order;
        this.console = Console.getInstance();
    }

    @Override
    public void execute() {

        handlePromotionAndDiscount();
        handleOrderPayment();
        order.setOrderStatus(new OrderPreparing());
        order.getCustomer().addLoyaltyPoints(order.getGrandTotal() * 0.01);
        console.success("Order has been placed.");
        OrderNotifier notifier = new OrderNotifier();
        notifier.addObserver(order, order.getCustomer());
        notifier.notifyObservers();
    }

    private void handleOrderPayment() {
        PaymentService payment = new PaymentService();
        do {
            String[] gateways = {"Cash Payment", "Credit Card", "Loyalty Points"};
            int paymentMethod = console.askOption(
                    "Select option:",
                    "Choose your preferred payment method? ",
                    gateways);

            switch (paymentMethod) {
                case 1:
                    payment.setPaymentGateway(new CashPayment());
                    break;
                case 2:
                    payment.setPaymentGateway(new CreditCardPayment());
                    break;
                case 3:
                    payment.setPaymentGateway(new LoyaltyPointsPayment());
                    break;
            }
            payment.pay(order);

        } while (order.getPaymentGateway() == null);
    }

    private void handlePromotionAndDiscount() {
        PromotionService newYear = new NewYearPromotion();
        PromotionService yearEnd = new YearEndPromotion();
        newYear.setNextHandler(yearEnd);
        newYear.applyDiscount(order);
    }

}
