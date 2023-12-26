package com.zemoso.campaign.service;

import com.zemoso.campaign.model.CampaignRun;
import com.zemoso.campaign.repository.CampaignRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@Service
public class CampaignRunService {

    @Autowired
    private CampaignRunRepository campaignRunRepository;

    public CampaignRun startCampaignRun(Long campaignId) {
        // Implement logic to start a campaign run
        // Set the start time, end time, and campaign ID
        CampaignRun campaignRun = new CampaignRun();
        campaignRun.setCampaignId(campaignId);
        campaignRun.setStartTime(ZonedDateTime.now(ZoneId.of("UTC")));

        return campaignRunRepository.save(campaignRun);
    }

    public CampaignRun endCampaignRun(Long campaignRunId) {
        // Implement logic to end a campaign run
        // Set the end time for the specified campaign run
        return campaignRunRepository.findById(campaignRunId)
                .map(campaignRun -> {
                    // Set end time and update the campaign run
                    campaignRun.setEndTime(ZonedDateTime.now(ZoneId.of("UTC")));
                    return campaignRunRepository.save(campaignRun);
                })
                .orElse(null); // Handle campaign run not found case
    }

    // Add additional methods as needed
}