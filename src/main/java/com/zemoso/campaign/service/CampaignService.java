package com.zemoso.campaign.service;

import com.zemoso.campaign.enums.CampaignStatus;
import com.zemoso.campaign.exception.NoActiveCampaignsException;
import com.zemoso.campaign.model.Campaign;
import com.zemoso.campaign.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public Campaign createCampaign(Campaign campaign) {
        validateCampaignParams(campaign);
        try {
            campaign.setStatus(CampaignStatus.READY);
            return campaignRepository.save(campaign);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create campaign", e);
        }
    }

    private void validateCampaignParams(Campaign campaign) {
        if (campaign.getContent() == null || campaign.getContent().isEmpty()) {
            throw new IllegalArgumentException("Missing or empty 'content' parameter");
        }

        if (campaign.getEmail_ids() == null || campaign.getEmail_ids().length == 0) {
            throw new IllegalArgumentException("Missing or empty 'email_ids' parameter");
        }

        if (campaign.getStartTime() == null) {
            throw new IllegalArgumentException("Missing 'startTime' parameter");
        }

        if (campaign.getEndTime() == null) {
            throw new IllegalArgumentException("Missing 'endTime' parameter");
        }

        if (campaign.getFrequency() == null) {
            throw new IllegalArgumentException("Missing 'frequency' parameter");
        }

        ZonedDateTime startTime = campaign.getStartTime();
        ZonedDateTime endTime = campaign.getEndTime();
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Invalid date range: startTime should be before endTime");
        }
    }

    public Campaign editCampaign(Long campaignId, Campaign updatedCampaign) throws NoSuchElementException {

        if(updatedCampaign.getStartTime() != null && updatedCampaign.getEndTime() != null) {
            ZonedDateTime startTime = updatedCampaign.getStartTime();
            ZonedDateTime endTime = updatedCampaign.getEndTime();
            if (startTime.isAfter(endTime)) {
                throw new IllegalArgumentException("Invalid date range: startTime should be before endTime");
            }
        }

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

    public List<Campaign> getCurrentActiveCampaigns() {
        ZonedDateTime todayStart = ZonedDateTime.now().with(LocalTime.MIN);
        ZonedDateTime tomorrowStart = ZonedDateTime.now().plusDays(1).with(LocalTime.MIN);
        List<Campaign> campaignList = campaignRepository.findByStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatusIn(
                todayStart, tomorrowStart, Arrays.asList(CampaignStatus.READY, CampaignStatus.RESUME));
        if (campaignList.isEmpty()) {
            throw new NoActiveCampaignsException("No active campaigns exist.");
        }
        return campaignList;
    }

    public List<Campaign> getAllActiveCampaigns() {
        List<Campaign> campaignList = campaignRepository.findAllByStatusIn(Arrays.asList(CampaignStatus.READY, CampaignStatus.RESUME));
        if (campaignList.isEmpty()) {
            throw new NoActiveCampaignsException("No active campaigns exist.");
        }
        return campaignList;
    }

    public Campaign updateCampaignStatus(Long campaignId, String status) {
        return campaignRepository.findById(campaignId)
                .map(existingCampaign -> {
                    CampaignStatus newStatus = CampaignStatus.valueOf(status.toUpperCase());
                    validateStatusTransition(existingCampaign.getStatus(), newStatus);
                    existingCampaign.setStatus(newStatus);
                    return campaignRepository.save(existingCampaign);
                })
                .orElseThrow(() -> new NoSuchElementException("No campaign found with id: " + campaignId));
    }

    private void validateStatusTransition(CampaignStatus currentStatus, CampaignStatus newStatus) {
        Map<CampaignStatus, Set<CampaignStatus>> allowedTransitions = new HashMap<>();
        allowedTransitions.put(CampaignStatus.READY, new HashSet<>(Arrays.asList(CampaignStatus.PAUSE, CampaignStatus.STOP)));
        allowedTransitions.put(CampaignStatus.PAUSE, new HashSet<>(Arrays.asList(CampaignStatus.RESUME, CampaignStatus.STOP)));
        allowedTransitions.put(CampaignStatus.RESUME, new HashSet<>(Arrays.asList(CampaignStatus.PAUSE)));
        if (!allowedTransitions.getOrDefault(currentStatus, Collections.emptySet()).contains(newStatus)) {
            throw new IllegalArgumentException("Invalid status transition from " + currentStatus + " to " + newStatus);
        }
    }


}
