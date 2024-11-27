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

    @OneToMany(mappedBy = "enclousure", cascade = CascadeType.ALL)
    private List<Dinosaur> dinosaurList;




}
