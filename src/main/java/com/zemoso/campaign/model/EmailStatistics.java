package com.zemoso.campaign.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmailStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
