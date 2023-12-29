package com.zemoso.campaign.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignRunId;
    @Column(name = "email_address")
    private String emailId;
    private Integer retryCount;
    private String status;
}
