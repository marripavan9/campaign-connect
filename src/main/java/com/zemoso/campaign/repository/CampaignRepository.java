package com.zemoso.campaign.repository;

import com.zemoso.campaign.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(String startTime, String endTime);
    List<Campaign> findAllByStatus(String status);

}
