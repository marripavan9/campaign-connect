# Campaign Management System

## Overview

The Campaign Management System is a web application designed to manage email campaigns. It provides functionalities to create, edit, and monitor email campaigns, as well as retrieve statistics on campaign performance.

## Features

- Create new email campaigns with specified content, recipients, start time, end time, and frequency.
- Edit existing campaigns to update content, recipients, start time, end time, and frequency.
- Retrieve a list of currently active campaigns based on the provided time range.
- Perform actions on campaigns, such as updating the campaign status.
- Retrieve campaign statistics, including successful and failed counts.

## Getting Started

### Prerequisites

Make sure you have the following tools installed on your system:

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or later)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://www.mysql.com/downloads/) database server


## Usage

### Create Campaign

Create a new email campaign by making a POST request to:

- **URL:** `POST /api/campaigns`
- **Headers:**
    - Content-Type: application/json
- **Request Body:**

  ```json
  {
    "content": "This is the new email content",
    "email_ids": ["test@mailinator.com", "test1@mailinator.com"],
    "startTime": "2023-12-13T12:00:00Z",
    "endTime": "2024-12-20T12:00:00Z",
    "frequency": 1
  }

### Edit Campaign

#### Request

- **URL:** `PUT /api/campaigns/{campaignId}`
- **Headers:**
    - Content-Type: application/json
- **Path Parameters:**
    - `{campaignId}`: ID of the campaign to be edited.
- **Request Body:**
  ```json
  {
    "content": "Updated content",
    "email_ids": ["updated@mailinator.com", "new@mailinator.com"],
    "startTime": "2023-12-15T12:00:00Z",
    "endTime": "2024-01-01T12:00:00Z",
    "frequency": 2
  }

### Get Current Active Campaigns

#### Request

- **URL:** `GET /api/campaigns`
- **Headers:**
    - Content-Type: application/json
- **Query Parameters:**
    - It will filter the campaigns based on dates and status.



### Get All Campaigns

#### Request

- **URL:** `GET /api/campaigns`
- **Headers:**
    - Content-Type: application/json


### Perform Campaign Action

#### Request

- **URL:** `PUT /api/campaigns/{campaignId}/status/{status}`
- **Headers:**
    - Content-Type: application/json
- **Query Parameters:**
    - `campaignId`: campaignId which you want to update.
    - `status`: status of campaign which you want to update, possible values are READY, RESUME, PAUSE and STOP.


###  Get Campaign Statistics

#### Request

- **URL:** `GET /api/campaigns/statistics`
- **Headers:**
    - Content-Type: application/json
- **Query Parameters:**
    - `startTime`: Start time for retrieving campaign statistics (Format: "yyyy-MM-dd'T'HH:mm:ssXXX").
    - `endTime`: End time for retrieving campaign statistics (Format: "yyyy-MM-dd'T'HH:mm:ssXXX").
