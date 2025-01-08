package com.dto;

public class DinosaurDTO {
    private Long Id;
    private String name;
    private String scientificName;
    private Double cost;
    private Long enclosure;
    private String image;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public Long getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Long enclosure) {
        this.enclosure = enclosure;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
