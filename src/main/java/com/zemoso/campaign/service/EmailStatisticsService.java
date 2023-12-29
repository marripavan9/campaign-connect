package com.zemoso.campaign.service;

import com.zemoso.campaign.dto.EmailStatistics;
import com.zemoso.campaign.dto.EmailStatisticsEntry;
import com.zemoso.campaign.model.CampaignRun;
import com.zemoso.campaign.repository.CampaignRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class EmailStatisticsService {

    @Autowired
    CampaignRunRepository campaignRunRepository;

    public EmailStatistics getStatistics(ZonedDateTime startTime, ZonedDateTime endTime) {
        // todo validations startTime > endTime
        List<CampaignRun> campaignRuns = campaignRunRepository.findFirstByStartTimeAfterAndEndTimeBeforeOrderByEndTimeDesc(startTime, endTime);

        // todo need to handle this aggregation based on date
        // 28-12-2023 start 12 pm end 12:15 pm success 5 fail 0 1
        // 28-12-2023 start 12 pm end 12:15 pm success 5 fail 0 2
        // 29-12-2023 start 12 pm end 12:15 pm success 5 fail 0 2
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
