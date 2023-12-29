package com.zemoso.campaign.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CampaignActionResponse {
    private String status;
    private String state;
    private String statusMessage;

    public CampaignActionResponse(String status, String state, String statusMessage) {
        this.status = status;
        this.state = state;
        this.statusMessage = statusMessage;
    }
}

