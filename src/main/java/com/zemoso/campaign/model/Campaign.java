package com.zemoso.campaign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zemoso.campaign.enums.CampaignStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column
    private String email_ids;

    @Enumerated(EnumType.STRING)
    private CampaignStatus status;

    @Column(name = "start_date")
    private ZonedDateTime startTime;

    @Column(name = "end_date")
    private ZonedDateTime endTime;

    private Integer frequency;

    @JsonIgnore
    public String getEmails() {
        return email_ids;
    }

    public void setEmail_ids(String[] emailIds) {
        this.email_ids = String.join(",", emailIds);
    }

    public String[] getEmail_ids() {
        return email_ids != null ? email_ids.split(",") : new String[0];
    }
}
