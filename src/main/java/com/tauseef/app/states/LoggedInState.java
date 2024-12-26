package com.tauseef.app.states;

import com.tauseef.app.contracts.ProfileState;

public class LoggedInState implements ProfileState {
    @Override
    public void display() {
        console.success("Welcome back! You have successfully logged in");
    }
}
