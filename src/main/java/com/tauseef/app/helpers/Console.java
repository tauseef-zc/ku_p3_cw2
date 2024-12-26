package com.tauseef.app.helpers;

import com.tauseef.app.enums.Color;

public class Console {

    private static final String INVALID_INPUT = "Invalid input. Please enter a valid number:";
    private static final String ENTER_VALID_NUMBER = "Please enter a valid number: ";
    private static final String ENTER_VALID_VALUE = "Please enter a valid value:";
    private static Console instance;
    private final java.io.Console scanner;

    public Console() {
        this.scanner = System.console();
    }

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
        return instance;
    }

    public void heading(String text) {
        String sb = String.valueOf(Color.WHITE_BACKGROUND) +
                Color.BLACK_BOLD +
                " " +
                text +
                " ";
        text(sb);
    }

    public void highlight(String text) {
        String sb = String.valueOf(Color.YELLOW_BACKGROUND) +
                Color.BLACK_BOLD +
                " " +
                text +
                " ";
        text(sb);
    }

    public void print(String text) {
        System.out.println(text + Color.RESET);
    }

    public void title(String text) {
        int textLength = text.length() + 2;
        int starCount = 10;
        int totalLength = textLength + (starCount * 2);
        String separator = "=".repeat(totalLength);
        String stars = "*".repeat(starCount);
        text(separator);
        text(Color.BLUE_BOLD + stars + " " + text + " " + stars);
        text(separator);
    }

    public String ask(String text) {
        return ask(text, false);
    }

    public String ask(String question, boolean isRequired) {
        question(question);
        while (true) {
            String answer = scanner.readLine().trim();
            if (!isRequired || !answer.isEmpty()) {
                return answer;
            }
            error(ENTER_VALID_VALUE);
        }
    }

    public String askPassword(String question) {
        java.io.Console console = System.console();
        while (true) {
            question(question);
            char[] passwordArray = console.readPassword();
            String password = new String(passwordArray);
            if (!password.isEmpty()) {
                console.flush();
                return password;
            }
            error(ENTER_VALID_VALUE);
        }
    }

    public int askNumber(String question, boolean isRequired) {
        question(question);
        while (true) {
            String value = scanner.readLine().trim();
            if (!isRequired && value.isEmpty()) {
                return 0;
            }
            try {
                int answer = Integer.parseInt(value);
                if (answer > 0 || !isRequired) {
                    return answer;
                }
                error(ENTER_VALID_NUMBER);
            } catch (NumberFormatException e) {
                error(INVALID_INPUT);
            }
        }
    }

    public boolean askBoolean(String question) {
        question(question);
        text(Color.CYAN + "[1]" + Color.RESET + " Yes      " + Color.CYAN + "[2]" + Color.RESET + " No");
        question("Please select an option:");
        while (true) {
            switch (scanner.readLine().trim()) {
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    error("Invalid option. Please enter 1 or 2:");
            }
        }
    }

    public int askOption(String question, String optionHeading, String[] options) {
        question(optionHeading);
        for (int i = 0; i < options.length; i++) {
            option(i + 1, options[i], false);
        }
        while (true) {
            int answer = askNumber(question, true);
            if (answer == 0 || answer > options.length) {
                error("Please enter a valid option.");
            } else {
                return answer;
            }
        }
    }

    public void success(String text) {
        text(Color.GREEN_BOLD + text);
    }

    public void error(String text) {
        text(Color.RED_BOLD + text);
    }

    public void question(String question) {
        text(Color.YELLOW_BOLD + question);
    }

    public void option(int id, String text, boolean newLine) {
        StringBuilder sb = new StringBuilder();
        sb.append(Color.CYAN).append('[').append(id).append("] ").append(Color.RESET).append(text);
        if (newLine) {
            sb.append('\n');
        }
        text(sb.toString());
    }

    public void text(String text) {
        System.out.println(text + Color.RESET);
    }

    public void emptySpace() {
        System.out.println();
    }
}
