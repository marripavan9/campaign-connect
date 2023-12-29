package com.zemoso.campaign.model;

import com.zemoso.campaign.enums.CampaignRunStatus;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignId;

    @Column(name = "success_count")
    private Integer successCount;

    @Column(name = "failure_count")
    private Integer failureCount;

    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    @Enumerated(EnumType.STRING)
    private CampaignRunStatus status;
}
