package com.zemoso.campaign.model;

public class ActionRequest {
    private String state;

    // Constructors, getters, and setters

    public ActionRequest() {
        // Default constructor for Jackson deserialization
    }

    public ActionRequest(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
