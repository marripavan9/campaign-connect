package com.zemoso.campaign.service;

import com.zemoso.campaign.model.Campaign;
import com.zemoso.campaign.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignRunService campaignRunService;

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign editCampaign(Long campaignId, Campaign updatedCampaign) {
        return campaignRepository.findById(campaignId)
                .map(existingCampaign -> {
                    if (updatedCampaign.getContent() != null) {
                        existingCampaign.setContent(updatedCampaign.getContent());
                    }
                    if (updatedCampaign.getEmail_ids() != null) {
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
                    if (updatedCampaign.getStatus() != null) {
                        existingCampaign.setStatus(updatedCampaign.getStatus());
                    }
                    if (updatedCampaign.getState() != null) {
                        existingCampaign.setState(updatedCampaign.getState());
                    }
                    return campaignRepository.save(existingCampaign);
                })
                .orElse(null);
    }

    public List<Campaign> getCurrentActiveCampaigns(String startTime, String  endTime) {
        return campaignRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(startTime, endTime);
    }

    public List<Campaign> getActiveCampaigns() {
        return campaignRepository.findAllByStatus("ready");
    }


    public String performCampaignAction(Long campaignId, String action) {
        return campaignRepository.findById(campaignId)
                .map(existingCampaign -> {
                    if (action != null && !action.isEmpty()) {
                        existingCampaign.setState(action);
                    }
                    return campaignRepository.save(existingCampaign).getState();
                })
                .orElse(null);
    }
}
