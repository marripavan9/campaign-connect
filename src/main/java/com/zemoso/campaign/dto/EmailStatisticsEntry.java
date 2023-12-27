package com.zemoso.campaign.dto;

import java.util.Date;

public class EmailStatisticsEntry {
    private Date date;
    private long successfulCount;
    private long failedCount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
