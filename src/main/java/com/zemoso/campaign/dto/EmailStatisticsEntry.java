package com.zemoso.campaign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class EmailStatisticsEntry {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private long successfulCount;
    private long failedCount;

    public void setDate(ZonedDateTime dateTime) {
        this.date = dateTime.toLocalDate();
    }
}
