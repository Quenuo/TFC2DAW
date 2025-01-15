package com.dto;

public class UserProfileDTO {
    private Long id;
    private String email;
    private String username;
    private String rol;
    private Boolean banned;
    private BanInfoDTO banInfoDTO;
    private String parkName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public BanInfoDTO getBanInfoDTO() {
        return banInfoDTO;
    }

    public void setBanInfoDTO(BanInfoDTO banInfoDTO) {
        this.banInfoDTO = banInfoDTO;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }
}
