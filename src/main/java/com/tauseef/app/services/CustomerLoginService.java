package com.tauseef.app.services;

import com.tauseef.app.contracts.ServiceInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.helpers.Validation;
import com.tauseef.app.models.Customer;
import com.tauseef.app.states.LoggedInState;
import com.tauseef.app.states.LoggedOutState;
import java.util.List;

public class CustomerLoginService implements ServiceInterface {

    final private Console console;
    final private List<Customer> customers;
    private final DashboardService dashboardService;
    private final Validation validation;
    private Customer loggedInCustomer;

    public CustomerLoginService(
            DashboardService dashboardService,
            Console console,
            List<Customer> customers
    ) {
        this.dashboardService = dashboardService;
        this.console = console;
        this.customers = customers;
        this.validation = Validation.getInstance();
    }

    @Override
    public void execute() {

        console.title("Customer Login");
        if (!handleLogin()) return;

        dashboardService.setCustomerService(this).execute();
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    private boolean handleLogin() {
        boolean isLoggedIn = true;
        do {
            String email = console.ask("Enter your email: ", true);
            if (!validation.isValidEmail(email) || !validation.isEmailExist(email, customers)) {
                String question = "The email you entered is invalid or not registered. Do you want to try again?";
                isLoggedIn = false;
                if (!console.askBoolean(question)) return false;
            } else {
                String password = console.askPassword("Enter your password: ");
                loggedInCustomer = login(email, password);

                if (loggedInCustomer == null) {
                    isLoggedIn = false;
                    boolean tryAgain = console.askBoolean("Invalid email or password. Do you want to try again?");
                    if (!tryAgain) return false;
                }

                if (loggedInCustomer != null && loggedInCustomer.getState() instanceof LoggedInState) {
                    isLoggedIn = true;
                }
            }

        } while (!isLoggedIn);

        return true;
    }

    public Customer login(String email, String password) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.isPasswordMatch(password)) {
                customer.setState(new LoggedInState());
                customer.getState().display();
                return customer;
            }
        }
        return null;
    }

    public void logout() {
        loggedInCustomer.setState(new LoggedOutState());
        loggedInCustomer.getState().display();
        loggedInCustomer = null;
    }
}
