package com.zemoso.campaign.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    // Convert the delimited string back to an array when retrieving from the database
    public String[] getEmail_ids() {
        return email_ids != null ? email_ids.split(",") : new String[0];
    }

    @Column(name = "start_date")
    private String startTime;
    @Column(name = "end_date")
    private String endTime;
    private Integer frequency;
    private String status;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;
}
