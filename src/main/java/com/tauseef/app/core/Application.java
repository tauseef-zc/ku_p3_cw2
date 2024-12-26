package com.tauseef.app.core;

import com.tauseef.app.helpers.Console;
import com.tauseef.app.models.Customer;
import com.tauseef.app.models.Order;
import com.tauseef.app.services.*;
import com.tauseef.app.states.OrderCompleted;
import com.tauseef.app.states.OrderPreparing;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Application {

    final private Console console;
    final private CustomerLoginService customerLoginService;
    final private CustomerRegisterService customerRegisterService;
    final private RatingAndFeedbackService ratingAndFeedbackService;
    final private List<Order> orders;

    final private String[] options = {
            "Customer Registration",
            "Customer Login",
            "Shop Ratings and Feedbacks",
            "Exit"
    };

    public Application(Console console) {
        this.console = console;
        this.orders = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        this.customerRegisterService = new CustomerRegisterService(console, customers);
        DashboardService dashboardService = new DashboardService(console, orders);
        this.customerLoginService = new CustomerLoginService(dashboardService, console, customers);
        this.ratingAndFeedbackService = new RatingAndFeedbackService(console, orders);
    }

    public void run() {
        try {
            displayWelcomeMessage();
            scheduleOrderCompletion();
            handleMenuService();
        } catch (Exception e) {
            console.error(e.getMessage());
        }
    }

    private void handleMenuService() {
        do {
            int option = console.askOption("Please select an option?", "Main Menu", this.options);
            console.emptySpace();
            switch (option) {
                case 1 -> this.customerRegisterService.execute();
                case 2 -> this.customerLoginService.execute();
                case 3 -> this.ratingAndFeedbackService.execute();
                case 4 -> exitApplication();
                default -> throw new IllegalStateException("Invalid option, please try again.");
            }
        } while (true);
    }

    private void scheduleOrderCompletion() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                OrderNotifier notifier = new OrderNotifier();
                orders.forEach(order -> {
                    if (order.getOrderStatus().equalsIgnoreCase(new OrderPreparing().getState())) {
                        order.setOrderStatus(new OrderCompleted());
                        notifier.addObserver(order, order.getCustomer());
                    }
                });
                notifier.notifyObservers();
            }
        }, 0, 45000);
    }

    private void displayWelcomeMessage() {
        console.heading(" PIZZA SHOP ");
        console.highlight(" Order Management System - Version 1.0 ");
        console.emptySpace();
    }

    private void exitApplication() {
        console.success("Thank you for using the PIZZA SHOP - Order Management System!");
        System.exit(0);
    }
}
