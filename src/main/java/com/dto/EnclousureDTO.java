package com.dto;

public class EnclousureDTO {
    private Long id;
    private String name;
    private String description;
    private Double cost;
    private String image;
    private Long dinosaurCount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDinosaurCount() {
        return dinosaurCount;
    }

    public void setDinosaurCount(Long dinosaurCount) {
        this.dinosaurCount = dinosaurCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
