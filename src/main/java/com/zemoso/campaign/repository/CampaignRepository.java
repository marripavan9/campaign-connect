package com.zemoso.campaign.repository;

import com.zemoso.campaign.enums.CampaignStatus;
import com.zemoso.campaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(ZonedDateTime startTime, ZonedDateTime endTime);
    List<Campaign> findAllByStatus(CampaignStatus campaignRunStatus);
}
