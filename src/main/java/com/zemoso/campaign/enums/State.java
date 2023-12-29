package com.zemoso.campaign.enums;

public enum State {
    READY("ready"),
    RESUME("resume"),
    PAUSE("pause"),
    STOP("stop");

    private final String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
