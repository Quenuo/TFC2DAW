package com.controller;

import com.Repository.DinosaurRepository;
import com.model.Dinosaur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/park/dinosaurs")
public class DinosaurController {
    private final DinosaurRepository dinosaurRepository;

    @Autowired
    public DinosaurController(DinosaurRepository dinosaurRepository){
        this.dinosaurRepository=dinosaurRepository;
    }

    @GetMapping
    public List<Dinosaur> getAllDinosaurs(){
        return dinosaurRepository.findAll();
    }
}
