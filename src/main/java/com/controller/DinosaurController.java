package com.controller;

import com.dto.DinosaurDTO;
import com.mapper.DinosaurMapper;
import com.repository.DinosaurRepository;
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
    private final DinosaurMapper dinosaurMapper;

    @Autowired
    public DinosaurController(DinosaurRepository dinosaurRepository,DinosaurMapper dinosaurMapper){
        this.dinosaurRepository=dinosaurRepository;
        this.dinosaurMapper=dinosaurMapper;
    }

    @GetMapping
    public List<DinosaurDTO> getAllDinosaurs(){
        return dinosaurMapper.toDTO(dinosaurRepository.findAll());

    }
}
