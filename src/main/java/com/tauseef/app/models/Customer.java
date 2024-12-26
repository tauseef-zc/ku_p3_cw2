package com.tauseef.app.models;

import com.tauseef.app.contracts.OrderObserver;
import com.tauseef.app.contracts.ProfileState;
import com.tauseef.app.helpers.Console;
import com.tauseef.app.states.GuestState;

public class Customer implements OrderObserver {

    private final String email;
    private final Console console;
    private String name;
    private String password;
    private String phone;
    private ProfileState profileState;
    private double loyaltyPoints = 0;

    public Customer(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.profileState = new GuestState();
        this.console = Console.getInstance();
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPasswordMatch(String password) {
        return this.password.equals(password);
    }

    public ProfileState getState() {
        return profileState;
    }

    public void setState(ProfileState state) {
        this.profileState = state;
    }

    public double getLoyaltyPoints() {
        return this.loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void addLoyaltyPoints(double amount) {
        this.loyaltyPoints += amount;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    @Override
    public void notify(String status) {
        console.success("<! " + name + ", your order status: " + status + " !>");
    }

}
