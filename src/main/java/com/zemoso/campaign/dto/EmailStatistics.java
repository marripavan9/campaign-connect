package com.zemoso.campaign.dto;

import com.zemoso.campaign.dto.EmailStatisticsEntry;

import java.util.ArrayList;
import java.util.List;


public class EmailStatistics {
    public List<EmailStatisticsEntry> getEntries() {
        return entries;
    }

    private List<EmailStatisticsEntry> entries = new ArrayList<>();

    public void addEntry(EmailStatisticsEntry entry) {
        entries.add(entry);
    }
}
