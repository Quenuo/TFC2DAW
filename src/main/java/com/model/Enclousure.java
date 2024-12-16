package com.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Enclousures")
public class Enclousure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(unique = true,nullable = false,length = 100)//en la nbase de datos se me olvido
            //añadirla como unica, pero al tener configuradao hibernete como auto en persistence.xml
            //ya se encara automáticamente de actualizar la columna y añadirla como unica
    private String name;

    @Column(name = "Description", nullable = false,length = 1000,unique = true)
    private String description;

    @Column(name = "Image",nullable = false)
    private String image;

    @Column(name = "Cost",nullable = false,columnDefinition = "DECIMAL(10,2)")
    private Double cost;


    @ManyToMany(mappedBy = "enclosures")
    private List<Park> parks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
