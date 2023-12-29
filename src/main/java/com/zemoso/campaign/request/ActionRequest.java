package com.zemoso.campaign.request;

import com.zemoso.campaign.enums.CampaignStatus;

public class ActionRequest {
    public CampaignStatus getState() {
        return campaignStatus;
    }

    public void setState(CampaignStatus campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    private CampaignStatus campaignStatus;
    private Long campaignId;

    public ActionRequest(CampaignStatus campaignStatus, Long campaignId) {
        this.campaignStatus = campaignStatus;
        this.campaignId = campaignId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }
}
