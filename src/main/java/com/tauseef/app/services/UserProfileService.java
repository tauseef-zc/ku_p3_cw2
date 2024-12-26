package com.tauseef.app.services;

import com.tauseef.app.contracts.ServiceInterface;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.models.Customer;

public class UserProfileService implements ServiceInterface {

    final Console console;
    private Customer customer;

    public UserProfileService(Console console) {
        this.console = console;
    }

    @Override
    public void execute() {
        console.title("Profile Update");
        handleProfileInformation();
        handleUpdatePassword();
    }

    private void handleProfileInformation() {
        if (console.askBoolean("Would you like to update your profile information?")) {
            String name = console.ask("Enter your name (" + customer.getName() + ")? leave blank to keep current name");
            String phone = console.ask("Enter your phone number (" + customer.getPhone() + ")? leave blank to keep current phone");
            customer.setName(name.isEmpty() ? customer.getName() : name)
                    .setPhone(phone.isEmpty() ? customer.getPhone() : phone);
            console.success("Your profile has been updated.");
        }
    }

    private void handleUpdatePassword() {
        if (console.askBoolean("Would you like to update your password?")) {
            boolean inCorrectPassword = true;
            do {
                String currentPassword = console.askPassword("Enter your current password: ");
                if (!customer.isPasswordMatch(currentPassword)) {
                    console.error("Invalid password. Please try again.");
                    if (!console.askBoolean("Would you like to try again?")) {
                        break;
                    }
                    continue;
                }

                inCorrectPassword = false;
                String password = console.askPassword("Enter your new password: ");
                String confirmPassword = console.askPassword("Confirm your new password: ");

                if (!password.equals(confirmPassword)) {
                    console.error("Passwords do not match. Please try again.");
                    inCorrectPassword = true;
                    continue;
                }

                customer.updatePassword(password);
                console.success("Your password has been updated.");

            } while (inCorrectPassword);
        }
    }

    public ServiceInterface setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }
}
