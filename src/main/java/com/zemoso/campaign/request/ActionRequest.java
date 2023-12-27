package com.zemoso.campaign.request;

public class ActionRequest {
    private String state;

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
