package com.zemoso.campaign.service;

import com.zemoso.campaign.enums.CampaignStatus;
import com.zemoso.campaign.model.Campaign;
import com.zemoso.campaign.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign createCampaign(Campaign campaign) {
        try {
            campaign.setStatus(CampaignStatus.READY);
            return campaignRepository.save(campaign);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create campaign", e);
        }
    }

    public Campaign editCampaign(Long campaignId, Campaign updatedCampaign) throws NoSuchElementException {
        return campaignRepository.findById(campaignId)
                .map(existingCampaign -> {
                    if (updatedCampaign.getContent() != null) {
                        existingCampaign.setContent(updatedCampaign.getContent());
                    }
                    if (updatedCampaign.getEmail_ids() != null && updatedCampaign.getEmail_ids().length > 0) {
                        existingCampaign.setEmail_ids(updatedCampaign.getEmail_ids());
                    }
                    if (updatedCampaign.getStartTime() != null) {
                        existingCampaign.setStartTime(updatedCampaign.getStartTime());
                    }
                    if (updatedCampaign.getEndTime() != null) {
                        existingCampaign.setEndTime(updatedCampaign.getEndTime());
                    }
                    if (updatedCampaign.getFrequency() != null) {
                        existingCampaign.setFrequency(updatedCampaign.getFrequency());
                    }
                    return campaignRepository.save(existingCampaign);
                })
                .orElseThrow(() -> new NoSuchElementException("No campaign found with id: " + campaignId));
    }

    public Campaign performCampaignAction(Long campaignId, CampaignStatus action) {
        try {
            // todo validate -> PAUSE -> READY is not allowed
            return campaignRepository.findById(campaignId)
                    .map(existingCampaign -> {
                        if (action != null && action.getValue() != null) {
                            existingCampaign.setStatus(action);
                        }
                        return campaignRepository.save(existingCampaign);
                    })
                    .orElse(null); // todo same applies here
        } catch (Exception e) {
            throw new RuntimeException("Failed to perform campaign action", e);
        }
    }

    public List<Campaign> getCurrentActiveCampaigns(ZonedDateTime startTime, ZonedDateTime  endTime) {
        try {
            return campaignRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(startTime, endTime);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get current active campaigns", e);
        }
    }

    public List<Campaign> getActiveCampaigns() {
        try {
            return campaignRepository.findAllByStatus(CampaignStatus.READY);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get active campaigns", e);
        }
    }

}
