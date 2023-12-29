package com.zemoso.campaign.exception;

public class NoActiveCampaignsException extends RuntimeException {
    public NoActiveCampaignsException(String message) {
        super(message);
    }
}
