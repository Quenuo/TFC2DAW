package com.dto;

import java.util.List;

public class ParkDTO {
    private Long id;
    private String name;
    private Double coin;
    private Long userId;
    private List<Long> enclosureIds;
    private List<Long> dinosaurIds;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getEnclosureIds() {
        return enclosureIds;
    }

    public void setEnclosureIds(List<Long> enclosureIds) {
        this.enclosureIds = enclosureIds;
    }

    public List<Long> getDinosaurIds() {
        return dinosaurIds;
    }

    public void setDinosaurIds(List<Long> dinosaurIds) {
        this.dinosaurIds = dinosaurIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCoin() {
        return coin;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }
}
