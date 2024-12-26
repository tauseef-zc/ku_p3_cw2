package com.tauseef.app.services;

import com.tauseef.app.contracts.ServiceInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.models.Order;
import java.util.List;

public class RatingAndFeedbackService implements ServiceInterface {

    final private Console console;
    final private List<Order> orders;

    public RatingAndFeedbackService(Console console, List<Order> orders) {
        this.console = console;
        this.orders = orders;
    }

    @Override
    public void execute() {
        console.title("Pizza Shop Ratings and Feedbacks");
        orders.forEach(order -> {
            console.print(order.getCustomer().getName() + " added feedback for order #" + order.getId());
            console.print("Feedback: " + order.getFeedback().getFeedback());
            console.print("Rating: " + order.getFeedback().getRating());
            console.print("-".repeat(56));
        });
    }
}
