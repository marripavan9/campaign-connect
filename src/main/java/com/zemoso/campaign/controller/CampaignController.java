package com.zemoso.campaign.controller;

import com.zemoso.campaign.dto.ApiResponse;
import com.zemoso.campaign.dto.EmailStatistics;
import com.zemoso.campaign.enums.State;
import com.zemoso.campaign.enums.Status;
import com.zemoso.campaign.model.Campaign;
import com.zemoso.campaign.request.ActionRequest;
import com.zemoso.campaign.service.CampaignService;
import com.zemoso.campaign.service.EmailStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private EmailStatisticsService emailStatisticsService;

    @PostMapping
    public ApiResponse<Campaign> createCampaign(@RequestBody Campaign campaign) {
        logger.info("Entered into createCampaign()");
        try {
            campaign.setState(State.READY);
            campaign.setStatus(Status.SUCCESS);
            Campaign createdCampaign = campaignService.createCampaign(campaign);
            logger.info("Exit from createCampaign()");
            return ApiResponse.success(createdCampaign, "Campaign created successfully");
        } catch (Exception e) {
            logger.error("Exception raised in createCampaign()", e);
            return ApiResponse.failure("Failed to create campaign");
        }
    }

    @GetMapping("/test")
    public ApiResponse<String> test() {
        return ApiResponse.success("Hello World", "Test successful");
    }

    @PutMapping("/{campaignId}")
    public ApiResponse<Campaign> editCampaign(@PathVariable Long campaignId, @RequestBody Campaign updatedCampaign) {
        logger.info("Entered into editCampaign()");
        try {
            Campaign editedCampaign = campaignService.editCampaign(campaignId, updatedCampaign);
            logger.info("Exit from editCampaign()");
            return ApiResponse.success(editedCampaign, "Campaign edited successfully");
        } catch (Exception e) {
            logger.error("Exception raised in editCampaign()", e);
            return ApiResponse.failure("Failed to edit campaign");
        }
    }

    @GetMapping("/active-campaigns")
    public ApiResponse<List<Campaign>> getCurrentActiveCampaigns(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime) {
        logger.info("Entered into getCurrentActiveCampaigns()");
        try {
            List<Campaign> activeCampaigns = campaignService.getCurrentActiveCampaigns(startTime, endTime);
            logger.info("Exit from getCurrentActiveCampaigns()");
            return ApiResponse.success(activeCampaigns, "Active campaigns retrieved successfully");
        } catch (Exception e) {
            logger.error("Exception raised in getCurrentActiveCampaigns()", e);
            return ApiResponse.failure("Failed to retrieve active campaigns");
        }
    }

    @GetMapping("/activeCampaigns")
    public ApiResponse<List<Campaign>> getActiveCampaigns() {
        logger.info("Entered into getActiveCampaigns()");
        try {
            List<Campaign> activeCampaigns = campaignService.getActiveCampaigns();
            logger.info("Exit from getActiveCampaigns()");
            return ApiResponse.success(activeCampaigns, "Active campaigns retrieved successfully");
        } catch (Exception e) {
            logger.error("Exception raised in getActiveCampaigns()", e);
            return ApiResponse.failure("Failed to retrieve active campaigns");
        }
    }

    @PostMapping("/state")
    public ApiResponse<Campaign> performCampaignAction(@RequestBody ActionRequest actionRequest) {
        logger.info("Entered into performCampaignAction()");
        try {
            Campaign campaign = campaignService.performCampaignAction(actionRequest.getCampaignId(), actionRequest.getState());
            logger.info("Exit from performCampaignAction()");
            return ApiResponse.success(campaign, "Campaign action performed successfully");
        } catch (Exception e) {
            logger.error("Exception raised in performCampaignAction()", e);
            return ApiResponse.failure("Failed to perform campaign action");
        }
    }

    @GetMapping("/statistics")
    public ApiResponse<EmailStatistics> getStatistics(@RequestParam String startTime, @RequestParam String endTime) {
        logger.info("Entered into getStatistics()");
        try {
            EmailStatistics statistics = emailStatisticsService.getStatistics(startTime, endTime);
            logger.info("Exit from getStatistics()");
            return ApiResponse.success(statistics, "Campaign statistics retrieved successfully");
        } catch (Exception e) {
            logger.error("Exception raised in getStatistics()", e);
            return ApiResponse.failure("Failed to retrive the statistics");
        }
    }
}