package com.zemoso.campaign.controller;

import com.zemoso.campaign.dto.EmailStatistics;
import com.zemoso.campaign.service.EmailStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class EmailStatisticsController {

    @Autowired
    private EmailStatisticsService emailStatisticsService;

    @GetMapping
    public EmailStatistics getStatistics(@RequestParam String startTime, @RequestParam String endTime) {
        return emailStatisticsService.getStatistics(startTime, endTime);
    }
}
