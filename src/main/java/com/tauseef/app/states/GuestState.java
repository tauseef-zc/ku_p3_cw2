package com.tauseef.app.states;

import com.tauseef.app.contracts.ProfileState;

public class GuestState implements ProfileState {
    @Override
    public void display() {
        console.success("Thank you! You have successfully registered");
    }
}