package com.tauseef.app.helpers;

import com.tauseef.app.enums.Color;
import com.tauseef.app.models.Order;

import java.time.format.DateTimeFormatter;

public class Invoice {

    private static final Console console = Console.getInstance();

    public static void showInvoice(Order order) {

        if (!console.askBoolean("Do you want to print the invoice?")) return;
        generateInvoice(order);
        console.print("Thank you for your order!");
        console.print("You earned " + (order.getGrandTotal() * 0.01) + " points for this order.");

    }

    public static void generateInvoice(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String title = "      PIZZA SHOP - INVOICE      ";
        String separator = "-".repeat(title.length() + 22);

        console.emptySpace();
        console.title(title);
        console.print("Invoice ID: #" + order.getId());
        console.print("Invoice Date: " + order.getOrderDate().format(formatter));
        console.print(separator);
        console.print(Color.WHITE_UNDERLINED + "Items");
        final int[] itemCount = {0};
        order.getOrderItems().forEach(pizza -> {
            itemCount[0]++;
            System.out.printf("%-44s %-12s%n",
                    itemCount[0] + ". " + pizza.getName() +
                            " [#" + pizza.getId() + "] (" + pizza.getCrust().name() + ")",
                    Formatter.currency(pizza.getPrice()));
            console.print("  - Toppings: " + pizza.getToppings().toString());
            console.print("  - Cheese: " + pizza.getCheese().toString());
        });
        console.print(separator);
        System.out.printf("%-44s %-12s%n", "Gross Total: ", Formatter.currency(order.getGrandTotal()));
        if (order.getPromotion() != null) {
            System.out.printf("%-44s %-12s%n",
                    "Discount (" + order.getPromotion().getPromotionName() + " - "
                            + order.getPromotion().getDiscountPercentage() + "%): ",
                    Formatter.currency(order.getDiscount()));
        }
        if (order.getRedeemedPoints() > 0) {
            System.out.printf("%-44s %-12s%n", "Loyalty Points Redeemed: ", Formatter.currency(order.getRedeemedPoints()));
            System.out.printf("%-44s %-12s%n", "Net Total: ", Formatter.currency(order.getFullTotal() - order.getRedeemedPoints()));
        } else {
            System.out.printf("%-44s %-12s%n", "Net Total: ", Formatter.currency(order.getFullTotal()));
        }
        console.print(separator);
    }
}
