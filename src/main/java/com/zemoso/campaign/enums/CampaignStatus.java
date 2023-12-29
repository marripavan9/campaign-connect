package com.zemoso.campaign.enums;

public enum CampaignStatus {
    READY("ready"),
    RESUME("resume"),
    PAUSE("pause"),
    STOP("stop");

    private final String value;

    CampaignStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
