package com.tauseef.app.services;

import com.tauseef.app.contracts.ServiceInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.helpers.Validation;
import com.tauseef.app.models.Customer;
import com.tauseef.app.states.GuestState;
import java.util.List;

public class CustomerRegisterService implements ServiceInterface {

    private final Console console;
    private final List<Customer> customers;
    private final Validation validation;

    public CustomerRegisterService(Console console, List<Customer> customers) {
        this.console = console;
        this.customers = customers;
        this.validation = Validation.getInstance();
    }

    @Override
    public void execute() {

        console.title(" Customer Registration ");
        String name = console.ask("Enter your name: ", true);
        String email = getEmail();
        if (email == null) return;

        String password = console.askPassword("Enter your password: ");
        String phone = console.ask("Enter your phone number: ", true);

        Customer customer = new Customer(name, email, password, phone);
        customer.setState(new GuestState());
        customer.getState().display();

        customers.add(customer);
        console.success("Please login to place an order.");

    }

    private String getEmail() {
        String email;
        boolean validEmail;
        do {
            email = console.ask("Enter your email: ", true);
            validEmail = validation.isValidEmail(email);

            if (!validEmail || validation.isEmailExist(email, customers)) {
                String question = "The email you entered is invalid or already exists. Do you want to try again?";
                if (!console.askBoolean(question)) {
                    return null;
                }
                validEmail = false;
            }

        } while (!validEmail);

        return email;
    }


}
