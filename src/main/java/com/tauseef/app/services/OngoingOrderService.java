package com.tauseef.app.services;

import com.tauseef.app.contracts.ServiceInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.helpers.Invoice;
import com.tauseef.app.models.Order;
import com.tauseef.app.states.OrderPreparing;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OngoingOrderService implements ServiceInterface {

    final Console console;
    private List<Order> orders;

    public OngoingOrderService(Console console) {
        this.console = console;
    }

    @Override
    public void execute() {
        console.title("Ongoing Orders");
        List<Order> ongoingOrders = orders.stream()
                .filter(order -> order.getOrderStatus().equals(new OrderPreparing().getState()))
                .toList();

        if (ongoingOrders.isEmpty()) {
            console.print("No ongoing orders for now.");

        } else {
            generateOngoingOrders(ongoingOrders);
            if (console.askBoolean("Would you like to view order details?")) {
                String orderId = console.ask("Enter order ID: ", true);
                orders.stream().filter(o -> o.getId().equals(orderId)).findFirst().ifPresent(Invoice::generateInvoice);
            }
        }

    }

    private void generateOngoingOrders(List<Order> orders) {
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

    public ServiceInterface setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
