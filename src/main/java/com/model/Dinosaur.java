package com.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Dinosaurs")
public class Dinosaur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name",nullable = false,unique = true,length = 100)
    private String name;

    @Column(name = "ScientificName",nullable = false,unique = true,length = 1000)
    private String scientificName;

    @Column(name = "Cost",nullable = false,columnDefinition = "DECIMAL(10,2)")
    private Double cost;

    @Column(name = "Image",nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "RequiredRecintoId", nullable = false)
    private Enclousure enclosure;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Enclousure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclousure enclosure) {
        this.enclosure = enclosure;
    }
}
