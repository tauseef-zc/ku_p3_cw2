package com.tauseef.app.enums;

public enum Color {

    RESET("\033[0m"),

    // Regular Colors
    CYAN("\033[0;36m"),

    // Bold Colors
    BLACK_BOLD("\033[1;30m"),
    RED_BOLD("\033[1;31m"),
    GREEN_BOLD("\033[1;32m"),
    YELLOW_BOLD("\033[1;33m"),
    BLUE_BOLD("\033[1;34m"),

    // Underlined Colors
    WHITE_UNDERLINED("\033[4;37m"),

    // Background Colors
    YELLOW_BACKGROUND("\033[43m"),
    WHITE_BACKGROUND("\033[47m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
