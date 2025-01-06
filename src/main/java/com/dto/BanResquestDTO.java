package com.dto;

public class BanResquestDTO {
    private String reason;
    private Long duration;

    public BanResquestDTO(){

    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
