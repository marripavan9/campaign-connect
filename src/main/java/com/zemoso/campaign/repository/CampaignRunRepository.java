package com.zemoso.campaign.repository;

import com.zemoso.campaign.model.CampaignRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRunRepository extends JpaRepository<CampaignRun, Long> {
    CampaignRun findTopByCampaignIdOrderByStartTimeDesc(Long campaignId);
}
