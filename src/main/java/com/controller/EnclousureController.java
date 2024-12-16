package com.controller;

import com.Repository.EnclousureRepository;
import com.model.Enclousure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/park/enclosures")
public class EnclousureController {

    private final EnclousureRepository enclousureRepository;

    public EnclousureController(EnclousureRepository enclousureRepository) {
        this.enclousureRepository = enclousureRepository;
    }

    @GetMapping
    public List<Enclousure> enclousureList(){
        return enclousureRepository.findAll();
    }


}
