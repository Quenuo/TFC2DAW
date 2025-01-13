package com.controller;

import com.dto.DinosaurDTO;
import com.dto.EnclousureDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Dinosaur;
import com.model.Emergency;
import com.model.Enclousure;
import com.services.ParkManagentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/management")
public class ParkManagentController {
    private final ParkManagentService parkManagentService;


    @Autowired
    public ParkManagentController(ParkManagentService parkManagentService){
        this.parkManagentService=parkManagentService;
    }

    @GetMapping("/dinosaurs")
    public List<DinosaurDTO> getAllDinosaurs() {
        return parkManagentService.getAllDinosaurs();
    }

    @PostMapping("/dinosaurs")
    public Dinosaur createDinosaur(@RequestPart("dinosaur") String dinosaurData,
                                   @RequestPart("image") MultipartFile image) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        DinosaurDTO dinosaurDTO = objectMapper.readValue(dinosaurData, DinosaurDTO.class);
        return parkManagentService.createDinosaur(dinosaurDTO, image);
    }

    @PutMapping("/dinosaurs/{id}")
    public Dinosaur updateDinosaur(@PathVariable("id") Long id,
                                   @RequestPart("dinosaur") String  dinosaurData,
                                   @RequestPart(value = "image",required = false) MultipartFile image) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DinosaurDTO dinosaurDTO = objectMapper.readValue(dinosaurData, DinosaurDTO.class);
        return parkManagentService.updateDinosaur(id,dinosaurDTO,image);
    }

    @DeleteMapping("/dinosaurs/{id}")
    public ResponseEntity<Void> deleteDinosaur(@PathVariable("id") Long id) {
        parkManagentService.deleteDinosaur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recintos")
    public List<EnclousureDTO> getAllEnclosures() {
        return parkManagentService.getAllEnclosures();
    }

    @PostMapping("/recintos")
    public Enclousure createEnclosure(@RequestPart("enclosure") String enclosureData,
                                      @RequestPart(value = "image") MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        EnclousureDTO enclousureDTO = objectMapper.readValue(enclosureData, EnclousureDTO.class);
        return parkManagentService.createEnclosure(enclousureDTO, image);
    }

    @PutMapping("/recintos/{id}")
    public Enclousure updateEnclosure(@PathVariable("id") Long id,
                                      @RequestPart("enclosure") String enclosureData,
                                      @RequestPart(value = "image",required = false) MultipartFile multipartFile) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        EnclousureDTO enclousureDTO = objectMapper.readValue(enclosureData, EnclousureDTO.class);
        return parkManagentService.updateEnclosure(id,enclousureDTO,multipartFile);
    }

    @DeleteMapping("/recintos/{id}")
    public ResponseEntity<Void> deleteEnclosure(@PathVariable("id") Long id) {
        parkManagentService.deleteEnclosure(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/emergencies")
    public List<Emergency> getAllEmergencies() {
        return parkManagentService.getAllEmergencies();
    }

    @PostMapping("/emergencies")
    public Emergency createEmergency(@RequestPart("emergency") String emergencyData,
                                   @RequestPart("image") MultipartFile image) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Emergency emergency = objectMapper.readValue(emergencyData, Emergency.class);
        return parkManagentService.createEmergency(emergency, image);
    }

    @PutMapping("/emergencies/{id}")
    public Emergency updateEmergency(@PathVariable("id") Long id,
                                   @RequestPart("emergency") String  emergencyData,
                                   @RequestPart(value = "image",required = false) MultipartFile image) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Emergency emergency = objectMapper.readValue(emergencyData, Emergency.class);
        return parkManagentService.updateEmergency(id,emergency,image);
    }

    @DeleteMapping("/emergencies/{id}")
    public ResponseEntity<Void> deleteEmergency(@PathVariable("id") Long id) {
        parkManagentService.deleteEmergency(id);
        return ResponseEntity.noContent().build();
    }


}
