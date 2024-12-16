package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

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





}
