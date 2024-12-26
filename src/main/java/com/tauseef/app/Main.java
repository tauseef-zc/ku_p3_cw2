package com.tauseef.app;

import com.tauseef.app.core.Application;
import com.tauseef.app.helpers.Console;

public class Main {

    public static void main(String[] args) {
        Console console = Console.getInstance();
        new Application(console).run();
    }
}