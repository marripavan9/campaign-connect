package com.zemoso.campaign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zemoso.campaign.enums.State;
import com.zemoso.campaign.enums.Status;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Enumerated(EnumType.STRING)
    private State state;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getFrequency() {
        return frequency;
    }
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

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

    @Column(name = "start_date")
    private ZonedDateTime startTime;
    @Column(name = "end_date")
    private ZonedDateTime endTime;
    private Integer frequency;
}
