package com.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Parks")
public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "ParkName",nullable = false,length = 100)
    private String name;

    @Column(name = "Coins",nullable = false,columnDefinition = "DECIMAL(10,2)")
    private Double coin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId",nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "Enclousures_Park",
            joinColumns = @JoinColumn(name = "ParkId"),
            inverseJoinColumns = @JoinColumn(name = "EnclousureId")
    )
    private List<Enclousure> enclosures;

    @ManyToMany
    @JoinTable(
            name = "Dinosaurs_Parks",
            joinColumns = @JoinColumn(name = "ParkId"),
            inverseJoinColumns = @JoinColumn(name = "DinosaurId")
    )
    private List<Dinosaur>  dinosaurList;



}
