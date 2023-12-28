package com.zemoso.campaign.controller;

import com.zemoso.campaign.dto.ApiResponse;
import com.zemoso.campaign.dto.EmailStatistics;
import com.zemoso.campaign.enums.State;
import com.zemoso.campaign.enums.Status;
import com.zemoso.campaign.model.Campaign;
import com.zemoso.campaign.request.ActionRequest;
import com.zemoso.campaign.service.CampaignService;
import com.zemoso.campaign.service.EmailStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            campaign.setState(State.READY);
            campaign.setStatus(Status.SUCCESS);
            Campaign createdCampaign = campaignService.createCampaign(campaign);
            return ApiResponse.success(createdCampaign, "Campaign created successfully");
        } catch (Exception e) {
            return ApiResponse.failure("Failed to create campaign");
        }
    }

    @GetMapping("/test")
    public ApiResponse<String> test() {
        return ApiResponse.success("Hello World", "Test successful");
    }

    @PutMapping("/{campaignId}")
    public ApiResponse<Campaign> editCampaign(@PathVariable Long campaignId, @RequestBody Campaign updatedCampaign) {
        try {
            Campaign editedCampaign = campaignService.editCampaign(campaignId, updatedCampaign);
            return ApiResponse.success(editedCampaign, "Campaign edited successfully");
        } catch (Exception e) {
            return ApiResponse.failure("Failed to edit campaign");
        }
    }

    @GetMapping("/active-campaigns")
    public ApiResponse<List<Campaign>> getCurrentActiveCampaigns(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime) {
        try {
            List<Campaign> activeCampaigns = campaignService.getCurrentActiveCampaigns(startTime, endTime);
            return ApiResponse.success(activeCampaigns, "Active campaigns retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.failure("Failed to retrieve active campaigns");
        }
    }

    @GetMapping("/activeCampaigns")
    public ApiResponse<List<Campaign>> getActiveCampaigns() {
        try {
            List<Campaign> activeCampaigns = campaignService.getActiveCampaigns();
            return ApiResponse.success(activeCampaigns, "Active campaigns retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.failure("Failed to retrieve active campaigns");
        }
    }

    @PostMapping("/state")
    public ApiResponse<Campaign> performCampaignAction(@RequestBody ActionRequest actionRequest) {
        try {
            Campaign campaign = campaignService.performCampaignAction(actionRequest.getCampaignId(), actionRequest.getState());
            return ApiResponse.success(campaign, "Campaign action performed successfully");
        } catch (Exception e) {
            return ApiResponse.failure("Failed to perform campaign action");
        }
    }

    @GetMapping("/statistics")
    public ApiResponse<EmailStatistics> getStatistics(@RequestParam String startTime, @RequestParam String endTime) {
        try {
            EmailStatistics statistics = emailStatisticsService.getStatistics(startTime, endTime);
            return ApiResponse.success(statistics, "Campaign statistics retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.failure("Failed to retrive the statistics");
        }
    }
}