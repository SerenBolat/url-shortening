package com.serenbolat.urlshortening.dto;

public class ResponseErrorDto {

    private String status;
    private String error;

    public ResponseErrorDto(String status, String error) {
        this.status = status;
        this.error = error;
    }

    public ResponseErrorDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "UrlErrorResponseDto{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
