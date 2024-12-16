package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Emergencies")
public class Emergency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name",nullable = false,unique = true,length = 100)
    private String name;

    @Column(name = "CoinLost",nullable = false,columnDefinition = "DECIMAL(10,2)")
    private Double coinLost;

    @Column(name = "Image",nullable = false)
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCoinLost() {
        return coinLost;
    }

    public void setCoinLost(Double coinLost) {
        this.coinLost = coinLost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
