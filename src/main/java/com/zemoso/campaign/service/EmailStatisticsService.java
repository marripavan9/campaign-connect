package com.zemoso.campaign.service;

import com.zemoso.campaign.dto.EmailStatistics;
import com.zemoso.campaign.dto.EmailStatisticsEntry;
import com.zemoso.campaign.model.CampaignRun;
import com.zemoso.campaign.repository.CampaignRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EmailStatisticsService {

    @Autowired
    CampaignRunRepository campaignRunRepository;

    public EmailStatistics getStatistics(ZonedDateTime startTime, ZonedDateTime endTime) {
        List<CampaignRun> campaignRuns = campaignRunRepository.findFirstByStartTimeAfterAndEndTimeBeforeOrderByEndTimeDesc(startTime, endTime);

        EmailStatistics emailStatistics = new EmailStatistics();

        for (CampaignRun campaignRun : campaignRuns) {
            EmailStatisticsEntry statistics = new EmailStatisticsEntry();
            statistics.setDate(campaignRun.getEndTime());
            statistics.setFailedCount(campaignRun.getFailureCount());
            statistics.setSuccessfulCount(campaignRun.getSuccessCount());
            emailStatistics.addEntry(statistics);
        }
        return emailStatistics;
    }

}
