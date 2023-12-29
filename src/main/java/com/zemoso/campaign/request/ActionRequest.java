package com.zemoso.campaign.request;

import com.zemoso.campaign.enums.State;

public class ActionRequest {
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;
    private Long campaignId;

    public ActionRequest(State state, Long campaignId) {
        this.state = state;
        this.campaignId = campaignId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }
}
