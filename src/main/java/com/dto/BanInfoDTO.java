package com.dto;

import java.time.LocalDateTime;

public class BanInfoDTO {
    private String reason;
    private LocalDateTime bannedAt;
    private LocalDateTime bannedUntil;

    public BanInfoDTO(String reason, LocalDateTime bannedAt, LocalDateTime bannedUntil) {
        this.reason = reason;
        this.bannedAt = bannedAt;
        this.bannedUntil = bannedUntil;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getBannedAt() {
        return bannedAt;
    }

    public void setBannedAt(LocalDateTime bannedAt) {
        this.bannedAt = bannedAt;
    }

    public LocalDateTime getBannedUntil() {
        return bannedUntil;
    }

    public void setBannedUntil(LocalDateTime bannedUntil) {
        this.bannedUntil = bannedUntil;
    }
}
