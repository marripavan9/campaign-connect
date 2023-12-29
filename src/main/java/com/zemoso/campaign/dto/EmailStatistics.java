package com.zemoso.campaign.dto;

import java.util.ArrayList;
import java.util.List;


public class EmailStatistics {
    public List<EmailStatisticsEntry> getStats() {
        return stats;
    }

    private List<EmailStatisticsEntry> stats = new ArrayList<>();

    public void addEntry(EmailStatisticsEntry entry) {
        stats.add(entry);
    }
}
