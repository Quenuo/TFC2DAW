package com.controller;

import com.repository.EmergencyRepository;
import com.model.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/park/emergencies")
public class EmergencyController {
    private Random random;
    private final EmergencyRepository emergencyRepository;

    @Autowired
    public EmergencyController(EmergencyRepository emergencyRepository){
        this.emergencyRepository=emergencyRepository;
        random=new Random();
    }
    @GetMapping
    public Emergency getRandomEmergency(){
        List<Emergency>emergencyList=emergencyRepository.findAll();
        return emergencyList.get(random.nextInt(0,emergencyList.size()));
    }
}
