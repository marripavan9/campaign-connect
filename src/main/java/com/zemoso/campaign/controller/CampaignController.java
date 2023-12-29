package com.zemoso.campaign.controller;

import com.zemoso.campaign.dto.ApiResponse;
import com.zemoso.campaign.dto.EmailStatistics;
import com.zemoso.campaign.model.Campaign;
import com.zemoso.campaign.request.ActionRequest;
import com.zemoso.campaign.service.CampaignService;
import com.zemoso.campaign.service.EmailStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private EmailStatisticsService emailStatisticsService;

    @PostMapping
    public ApiResponse<Campaign> createCampaign(@RequestBody Campaign campaign) {
        try {
            Campaign createdCampaign = campaignService.createCampaign(campaign);
            return ApiResponse.success(createdCampaign, "Campaign created successfully");
        } catch (Exception e) {
            return handleException(e, "Failed to create campaign");
        }
    }

    @PutMapping("/{campaignId}")
    public ApiResponse<Campaign> editCampaign(@PathVariable Long campaignId, @RequestBody Campaign updatedCampaign) {
        try {
            Campaign editedCampaign = campaignService.editCampaign(campaignId, updatedCampaign);
            return ApiResponse.success(editedCampaign, "Campaign edited successfully");
        } catch (Exception e) {
            return handleException(e, "Failed to edit campaign");
        }
    }

    // todo renaming is required
    @GetMapping("/active-campaigns") // todo not required
    public ApiResponse<List<Campaign>> getCurrentActiveCampaigns(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endTime) {
        try {
            List<Campaign> activeCampaigns = campaignService.getCurrentActiveCampaigns(startTime, endTime);
            return ApiResponse.success(activeCampaigns, "Active campaigns retrieved successfully");
        } catch (Exception e) {
            return handleException(e, "Failed to retrieve active campaigns");
        }
    }

    // todo getAllCampaigns - naming issue
    @GetMapping("/activeCampaigns") // todo getAllCampaigns - current time and status
    public ApiResponse<List<Campaign>> getActiveCampaigns() {
        try {
            List<Campaign> activeCampaigns = campaignService.getActiveCampaigns();
            return ApiResponse.success(activeCampaigns, "Active campaigns retrieved successfully");
        } catch (Exception e) {
            return handleException(e, "Failed to retrieve active campaigns");
        }
    }

    // campaigns/performAction/campaignId/state
    // todo PUT
    @PostMapping("/state") // todo naming change
    // todo take campaignId and state as path variable
    public ApiResponse<Campaign> performCampaignAction(@RequestBody ActionRequest actionRequest) {
        try {
            Campaign campaign = campaignService.performCampaignAction(actionRequest.getCampaignId(), actionRequest.getState());
            return ApiResponse.success(campaign, "Campaign action performed successfully");
        } catch (Exception e) {
            return handleException(e, "Failed to perform campaign action");
        }
    }

    @GetMapping("/statistics")
    public ApiResponse<EmailStatistics> getStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endTime) {
        try {
            EmailStatistics statistics = emailStatisticsService.getStatistics(startTime, endTime);
            return ApiResponse.success(statistics, "Campaign statistics retrieved successfully");
        } catch (Exception e) {
            return handleException(e, "Failed to retrieve the statistics");
        }
    }

    private <T> ApiResponse<T> handleException(Exception e, String errorMessage) {
        return ApiResponse.failure(errorMessage);
    }
}
