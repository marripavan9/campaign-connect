package com.zemoso.campaign.dto;

public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    public String getStatus() {
        return status;
    }

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>("failure", message, null);
    }
}
