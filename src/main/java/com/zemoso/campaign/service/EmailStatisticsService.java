package com.zemoso.campaign.service;

import com.zemoso.campaign.dto.EmailStatistics;
import com.zemoso.campaign.dto.EmailStatisticsEntry;
import com.zemoso.campaign.model.CampaignRun;
import com.zemoso.campaign.repository.CampaignRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailStatisticsService {

    @Autowired
    CampaignRunRepository campaignRunRepository;

    public EmailStatistics getStatistics(ZonedDateTime startTime, ZonedDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Invalid date range: startTime should be before endTime");
        }

        List<CampaignRun> campaignRuns = campaignRunRepository.findByStartTimeBeforeAndEndTimeAfterOrderByEndTimeDesc(endTime, startTime);
        Map<LocalDate, EmailStatisticsEntry> aggregatedStatistics = new HashMap<>();

        for (CampaignRun campaignRun : campaignRuns) {
            LocalDate date = campaignRun.getEndTime().toLocalDate();
            EmailStatisticsEntry statistics = aggregatedStatistics.getOrDefault(date, new EmailStatisticsEntry());
            statistics.setDate(campaignRun.getEndTime());
            statistics.setFailedCount(statistics.getFailedCount() + campaignRun.getFailureCount());
            statistics.setSuccessfulCount(statistics.getSuccessfulCount() + campaignRun.getSuccessCount());
            aggregatedStatistics.put(date, statistics);
        }

        if (aggregatedStatistics.isEmpty()) {
            throw new IllegalArgumentException("No statistics available for the specified date range");
        }

        try {
            EmailStatistics emailStatistics = new EmailStatistics();
            emailStatistics.setEntries(new ArrayList<>(aggregatedStatistics.values()));
            return emailStatistics;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get statistics", e);
        }
    }
}
