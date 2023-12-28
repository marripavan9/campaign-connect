package com.zemoso.campaign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zemoso.campaign.enums.State;
import com.zemoso.campaign.enums.Status;

import javax.persistence.*;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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
    private String startTime;
    @Column(name = "end_date")
    private String endTime;
    private Integer frequency;
}
