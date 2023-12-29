package com.zemoso.campaign.enums;

public enum CampaignRunStatus {

    SUCCESS("success"),
    FAILURE("failure"),
    RUNNING("running");

    private final String value;

    CampaignRunStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
