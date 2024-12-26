package com.tauseef.app.services;

import com.tauseef.app.builders.PizzaBuilder;
import com.tauseef.app.commands.CancelOrderCommand;
import com.tauseef.app.commands.OrderCommand;
import com.tauseef.app.commands.PlaceOrderCommand;
import com.tauseef.app.contracts.FeedbackInterface;
import com.tauseef.app.contracts.ServiceInterface;
import com.tauseef.app.enums.SizeEnum;
import com.tauseef.app.feedback.BasicFeedback;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.helpers.Formatter;
import com.tauseef.app.helpers.Invoice;
import com.tauseef.app.models.BasePizza;
import com.tauseef.app.models.Customer;
import com.tauseef.app.models.Order;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderCreationService implements ServiceInterface {

    final Console console;
    final List<BasePizza> pizzaList = new ArrayList<>();
    List<Order> orders;
    private Customer customer;

    public OrderCreationService(Console console) {
        this.console = console;
        this.initMenu();
    }

    public OrderCreationService setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public ServiceInterface setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Override
    public void execute() {

        List<BasePizza> orderPizza = new ArrayList<>();
        takeOrder(orderPizza);
        displayOrderSummary(orderPizza);
        Order order = handlePlaceOrder(orderPizza);
        if (order == null) return;
        showInvoice(order);
        handleOrderFeedback(order);
    }

    private void handleOrderFeedback(Order order) {
        while (true) {
            int rating = console.askNumber("Rate your experience (1-5): ", true);
            if (rating < 1 || rating > 5) {
                console.error("Invalid rating. Please enter a number between 1 and 5.");
                continue;
            }

            String message = console.ask("Write your feedback: ", true);
            FeedbackInterface feedback = new RatingService(new BasicFeedback(message), rating);
            order.addFeedback(feedback);
            console.print("Feedback added successfully!");
            break;
        }
    }


    private Order handlePlaceOrder(List<BasePizza> orderPizza) {

        Order order = new Order(customer, orderPizza);

        OrderCommand orderCommand = new OrderCommand();
        boolean command = console.askBoolean("Do you want to place the order?");
        if (command) {
            orderCommand.setCommand(new PlaceOrderCommand(order));
        } else {
            orderCommand.setCommand(new CancelOrderCommand(order));
            return null;
        }
        orderCommand.run();

        order.setOrderDate(LocalDateTime.now());
        orders.add(order);

        return order;
    }

    private void takeOrder(List<BasePizza> orderPizza) {
        console.heading("Create a New Order");

        boolean addMore = true;
        do {
            BasePizza selectedPizza = getSelectedPizza();
            orderPizza.add(selectedPizza);
            console.print("Your selected: " + selectedPizza);

            if (!console.askBoolean("Do you want to add another pizza?")) {
                addMore = false;
            }

        } while (addMore);
    }

    private void displayOrderSummary(List<BasePizza> orderPizza) {
        console.emptySpace();
        console.highlight("Order Summary: ");
        orderPizza.forEach(pizza -> {
            console.print("-".repeat(50));
            console.print((orderPizza.indexOf(pizza) + 1) + ". " + pizza.getName() +
                    " [#" + pizza.getId() + "] (" + pizza.getCrust().name() + ") - " +
                    Formatter.currency(pizza.getPrice()));
            console.print("  - Toppings: " + pizza.getToppings().toString());
            console.print("  - Cheese: " + pizza.getCheese().toString());
        });
        console.print("-".repeat(50));
        console.emptySpace();
    }

    private BasePizza getSelectedPizza() {
        String[] pizzaMenu = pizzaList.stream().map(BasePizza::getName).toList().toArray(new String[0]);
        int pizzaItem = console.askOption("What pizza would you like to order: ", "Pizza Menu", pizzaMenu) - 1;

        BasePizza selectedPizza = pizzaList.get(pizzaItem);
        console.print("You selected the " + selectedPizza.getName() + " pizza.");

        String[] sizes = Arrays.stream(SizeEnum.values()).map(SizeEnum::name).toArray(String[]::new);
        int size = console.askOption("Select the crust: ", "What crust would you like?", sizes) - 1;
        selectedPizza.setCrust(SizeEnum.fromCode(size));
        console.print("You selected the " + SizeEnum.fromCode(size) + " crust.");

        if (console.askBoolean("Would you like to add extra toppings?")) {
            boolean addMore;
            do {
                selectedPizza.addToppings(console.ask("Add a topping: ", true));
                addMore = console.askBoolean("Add more toppings?:");
            } while (addMore);
        }

        if (console.askBoolean("Would you like to add extra cheese?")) {
            size = console.askOption("Select the portion?", "Cheese portions:", sizes) - 1;
            selectedPizza.addCheese(SizeEnum.fromCode(size));
        }
        return selectedPizza;
    }

    private void showInvoice(Order order) {
        Invoice.showInvoice(order);
    }

    private void initMenu() {
        pizzaList.add(new PizzaBuilder()
                .setName("Margherita")
                .setBasePrice(600)
                .addTopping("Smoked Chicken")
                .addTopping("Bell Pepper")
                .addCheese(SizeEnum.SMALL)
                .addSauce(true)
                .build());

        pizzaList.add(new PizzaBuilder()
                .setName("Hot and Spicy")
                .setBasePrice(800)
                .addTopping("Spicy Chicken")
                .addTopping("Mushroom")
                .addCheese(SizeEnum.SMALL)
                .addSauce(true)
                .build());

        pizzaList.add(new PizzaBuilder()
                .setName("BBQ Chicken")
                .setBasePrice(1000)
                .addTopping("BBQ Chicken")
                .addTopping("Onion")
                .addCheese(SizeEnum.SMALL)
                .addSauce(true)
                .build());
    }
}
