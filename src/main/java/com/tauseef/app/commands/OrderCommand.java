package com.tauseef.app.commands;

import com.tauseef.app.contracts.CommandInterface;

public class OrderCommand {

    private CommandInterface command;

    public void setCommand(CommandInterface command) {
        this.command = command;
    }

    public void run() {
        command.execute();
    }
}
