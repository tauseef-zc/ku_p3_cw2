package com.tauseef.app.states;

import com.tauseef.app.contracts.ProfileState;

public class LoggedOutState implements ProfileState {
    @Override
    public void display() {
        console.success("You have successfully logged out!");
    }
}
