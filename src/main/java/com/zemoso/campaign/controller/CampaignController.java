package com.zemoso.campaign.controller;

import com.zemoso.campaign.dto.CampaignActionResponse;
import com.zemoso.campaign.request.ActionRequest;
import com.zemoso.campaign.model.Campaign;
import com.zemoso.campaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @PostMapping
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @PutMapping("/{campaignId}")
    public Campaign editCampaign(@PathVariable Long campaignId, @RequestBody Campaign updatedCampaign) {
        return campaignService.editCampaign(campaignId, updatedCampaign);
    }

    @GetMapping("/active-campaigns")
    public List<Campaign> getCurrentActiveCampaigns(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime) {
        return campaignService.getCurrentActiveCampaigns(startTime, endTime);
    }

    @GetMapping("/activeCampaigns")
    public List<Campaign> getActiveCampaigns() {
        return campaignService.getActiveCampaigns();
    }

    @PostMapping("/{campaignId}/state")
    public CampaignActionResponse performCampaignAction(@PathVariable Long campaignId, @RequestBody ActionRequest actionRequest) {
        String state = campaignService.performCampaignAction(campaignId, actionRequest.getState());
        return new CampaignActionResponse("success", state, "State Updated Successfully");

    }
}