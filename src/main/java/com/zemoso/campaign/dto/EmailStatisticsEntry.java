package com.zemoso.campaign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class EmailStatisticsEntry {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private long successfulCount;
    private long failedCount;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(ZonedDateTime dateTime) {
        this.date = dateTime.toLocalDate();
    }

    public long getSuccessfulCount() {
        return successfulCount;
    }

    public void setSuccessfulCount(long successfulCount) {
        this.successfulCount = successfulCount;
    }

    public long getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(long failedCount) {
        this.failedCount = failedCount;
    }
}
