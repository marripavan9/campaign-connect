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

    public EmailStatistics getStatistics(String startTime, String endTime) {
        List<CampaignRun> campaignRuns = campaignRunRepository.findFirstByStartTimeAfterAndEndTimeBeforeOrderByEndTimeDesc(ZonedDateTime.parse(startTime),
                ZonedDateTime.parse(endTime));

        EmailStatistics emailStatistics = new EmailStatistics();

        for (CampaignRun campaignRun : campaignRuns) {
            EmailStatisticsEntry statistics = new EmailStatisticsEntry();
            statistics.setDate(Date.from(campaignRun.getEndTime().toInstant()));
            statistics.setFailedCount(campaignRun.getFailureCount());
            statistics.setSuccessfulCount(campaignRun.getSuccessCount());
            emailStatistics.addEntry(statistics);
        }
        return emailStatistics;
    }

}
