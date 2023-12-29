package com.zemoso.campaign.controller;

import com.zemoso.campaign.dto.ApiResponse;
import com.zemoso.campaign.dto.EmailStatistics;
import com.zemoso.campaign.exception.NoActiveCampaignsException;
import com.zemoso.campaign.model.Campaign;
import com.zemoso.campaign.service.CampaignService;
import com.zemoso.campaign.service.EmailStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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
        } catch (EntityNotFoundException e) {
            return ApiResponse.failure("Campaign not found with id: " + campaignId);
        } catch (Exception e) {
            return handleException(e, "Failed to edit campaign");
        }
    }

    @GetMapping("")
    public ApiResponse<List<Campaign>> getCurrentActiveCampaigns() {
        try {
            List<Campaign> activeCampaigns = campaignService.getCurrentActiveCampaigns();
            return ApiResponse.success(activeCampaigns, "Active campaigns retrieved successfully");
        } catch (NoActiveCampaignsException e) {
            return ApiResponse.failure("No active campaigns found");
        }  catch (Exception e) {
            return handleException(e, "Failed to retrieve active campaigns");
        }
    }

    @GetMapping("/getAllCampaigns")
    public ApiResponse<List<Campaign>> getAllCampaigns() {
        try {
            List<Campaign> activeCampaigns = campaignService.getActiveCampaigns();
            return ApiResponse.success(activeCampaigns, "Campaigns retrieved successfully");
        } catch (NoActiveCampaignsException e) {
            return ApiResponse.failure("No campaigns found");
        } catch (Exception e) {
            return handleException(e, "Failed to retrieve active campaigns");
        }
    }

    @PutMapping("/{campaignId}/status/{status}")
    public ApiResponse<Campaign> updateCampaignStatus(
            @PathVariable Long campaignId,
            @PathVariable String status) {
        try {
            Campaign updatedCampaign = campaignService.updateCampaignStatus(campaignId, status);
            return ApiResponse.success(updatedCampaign, "Campaign status updated successfully");
        } catch (NoSuchElementException | EntityNotFoundException e) {
            return ApiResponse.failure("Campaign not found with id: " + campaignId);
        } catch (IllegalArgumentException e) {
            return ApiResponse.failure(e.getMessage());
        } catch (Exception e) {
            return handleException(e, "Failed to update campaign status");
        }
    }

    @GetMapping("/statistics")
    public ApiResponse<EmailStatistics> getStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endTime) {
        try {
            EmailStatistics statistics = emailStatisticsService.getStatistics(startTime, endTime);
            return ApiResponse.success(statistics, "Campaign statistics retrieved successfully");
        } catch (IllegalArgumentException e) {
            return ApiResponse.failure(e.getMessage());
        } catch (Exception e) {
            return handleException(e, "Failed to retrieve the statistics");
        }
    }

    private <T> ApiResponse<T> handleException(Exception e, String errorMessage) {
        return ApiResponse.failure(errorMessage);
    }
}
