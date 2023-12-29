package com.zemoso.campaign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zemoso.campaign.enums.State;
import com.zemoso.campaign.enums.Status;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "start_date")
    private ZonedDateTime startTime;

    @Column(name = "end_date")
    private ZonedDateTime endTime;

    private Integer frequency;

    @Column
    private String email_ids;

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
