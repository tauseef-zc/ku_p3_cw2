package com.tauseef.app.helpers;

import com.tauseef.app.models.Customer;

import java.util.List;

public class Validation {
    private static Validation instance;

    public static Validation getInstance() {
        if (instance == null) {
            instance = new Validation();
        }
        return instance;
    }

    public boolean isValidEmail(String email) {
        //Checking the email format
        return email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    }

    public boolean isEmailExist(String email, List<Customer> customers) {
        //Checking the email with existing emails in the system
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
