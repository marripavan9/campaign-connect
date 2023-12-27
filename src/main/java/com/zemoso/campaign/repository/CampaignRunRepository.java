package com.zemoso.campaign.repository;

import com.zemoso.campaign.model.CampaignRun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface CampaignRunRepository extends JpaRepository<CampaignRun, Long> {
    CampaignRun findTopByCampaignIdOrderByStartTimeDesc(Long campaignId);
    List<CampaignRun> findFirstByStartTimeAfterAndEndTimeBeforeOrderByEndTimeDesc(
            ZonedDateTime startTime, ZonedDateTime endTime);

}
