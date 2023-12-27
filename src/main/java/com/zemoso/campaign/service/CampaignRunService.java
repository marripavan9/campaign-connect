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
        CampaignRun campaignRun = new CampaignRun();
        campaignRun.setCampaignId(campaignId);
        campaignRun.setStartTime(ZonedDateTime.now(ZoneId.of("UTC")));
        return campaignRunRepository.save(campaignRun);
    }

    public CampaignRun endCampaignRun(Long campaignRunId) {
        return campaignRunRepository.findById(campaignRunId)
                .map(campaignRun -> {
                    campaignRun.setEndTime(ZonedDateTime.now(ZoneId.of("UTC")));
                    return campaignRunRepository.save(campaignRun);
                })
                .orElse(null);
    }
}