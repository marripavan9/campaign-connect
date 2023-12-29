package com.zemoso.campaign.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmailStatistics {

    private List<EmailStatisticsEntry> entries;

    public EmailStatistics() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(EmailStatisticsEntry entry) {
        this.entries.add(entry);
    }
}
