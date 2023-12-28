package com.zemoso.campaign.dto;

public class CampaignActionResponse {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    private String state;
    private String statusMessage;

    public CampaignActionResponse(String status, String state, String statusMessage) {
        this.status = status;
        this.state = state;
        this.statusMessage = statusMessage;
    }

}
