package com.zemoso.campaign.request;

public class ActionRequest {
    private String state;
    private Long campaignId;

    public ActionRequest(String state, Long campaignId) {
        this.state = state;
        this.campaignId = campaignId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
