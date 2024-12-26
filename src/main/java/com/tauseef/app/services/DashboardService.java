package com.tauseef.app.services;

import com.tauseef.app.contracts.ServiceInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.models.Customer;
import com.tauseef.app.models.Order;
import java.util.List;

public class DashboardService implements ServiceInterface {

    private final Console console;
    private final List<Order> orders;
    private final UserProfileService userProfileService;
    private final OrderCreationService orderCreationService;
    private final OngoingOrderService ongoingOrderService;
    private final OrderHistoryService orderHistoryService;
    final private String[] options = {
            "Create a new order",
            "View ongoing orders",
            "View order history",
            "Profile Update",
            "Logout"
    };
    private CustomerLoginService customerLoginService;

    public DashboardService(Console console, List<Order> orders) {
        this.console = console;
        this.orders = orders;
        this.orderCreationService = new OrderCreationService(console);
        this.ongoingOrderService = new OngoingOrderService(console);
        this.orderHistoryService = new OrderHistoryService(console);
        this.userProfileService = new UserProfileService(console);
    }

    public ServiceInterface setCustomerService(CustomerLoginService customerLoginService) {
        this.customerLoginService = customerLoginService;
        return this;
    }

    @Override
    public void execute() {
        console.emptySpace();
        Customer customer = customerLoginService.getLoggedInCustomer();

        while (true) {

            List<Order> customersOrders = orders.stream()
                    .filter(order -> order.getCustomer().equals(customer))
                    .toList();

            int option = console.askOption(
                    "Choose an option: ",
                    "What would you like to do?",
                    options);

            switch (option) {
                case 1:
                    orderCreationService.setCustomer(customer).setOrders(orders).execute();
                    break;
                case 2:
                    ongoingOrderService.setOrders(customersOrders).execute();
                    break;
                case 3:
                    orderHistoryService.setOrders(customersOrders).execute();
                    break;
                case 4:
                    userProfileService.setCustomer(customer).execute();
                    break;
                case 5:
                    customerLoginService.logout();
                    return;
            }
        }
    }

}
