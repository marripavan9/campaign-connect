package com.zemoso.campaign.enums;

public enum Status {
    SUCCESS("success"),
    FAILURE("failure"),
    RUNNING("running");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
