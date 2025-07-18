package com.example.KinoLargo_frontend.enums;

public enum State {
    SALE("За продажба"),
    RENT("Под наем");

    private final String displayText;

    State(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
