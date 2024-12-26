package com.tauseef.app.services;

import com.tauseef.app.contracts.ServiceInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.helpers.Invoice;
import com.tauseef.app.models.Order;
import com.tauseef.app.states.OrderCompleted;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderHistoryService implements ServiceInterface {

    final Console console;
    private List<Order> orders;

    public OrderHistoryService(Console console) {
        this.console = console;
    }

    @Override
    public void execute() {
        console.title("Order History");
        List<Order> pastOrders = orders.stream()
                .filter(order -> order.getOrderStatus().equals(new OrderCompleted().getState()))
                .toList();

        if (pastOrders.isEmpty()) {
            console.print("No past orders for now.");

        } else {
            generateOrderHistory(pastOrders);
            if (console.askBoolean("Would you like to view the past order details?")) {
                String orderId = console.ask("Enter order ID: ", true);
                orders.stream().filter(o -> o.getId().equals(orderId)).findFirst().ifPresent(Invoice::generateInvoice);
            }
        }
    }

    private void generateOrderHistory(List<Order> orders) {
        console.print("-".repeat(82));
        System.out.printf("%-6s | %-18s | %-16s | %-18s | %-14s%n",
                "ID",
                "Order Date",
                "Order Status",
                "Payment",
                "Order Total");
        console.print("-".repeat(82));
        orders.forEach(order -> System.out.printf("%-6s | %-18s | %-16s | %-18s | %-14s%n",
                order.getId(),
                order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                order.getOrderStatus(),
                order.getPaymentGateway().getName(),
                order.getFullTotal()
        ));
        console.emptySpace();
    }

    public ServiceInterface setOrders(List<Order> customersOrders) {
        this.orders = customersOrders;
        return this;
    }
}
