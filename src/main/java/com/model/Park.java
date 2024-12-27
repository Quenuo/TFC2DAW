package com.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Parks")
public class Park {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "ParkName",length = 100)
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

    public Double getCoin() {
        return coin;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }

    public Long getUserId() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Enclousure> getEnclosures() {
        return enclosures;
    }

    public void setEnclosures(List<Enclousure> enclosures) {
        this.enclosures = enclosures;
    }

    public List<Dinosaur> getDinosaurList() {
        return dinosaurList;
    }

    public void setDinosaurList(List<Dinosaur> dinosaurList) {
        this.dinosaurList = dinosaurList;
    }
}
