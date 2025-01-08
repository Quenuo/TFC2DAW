package com.controller;

import com.dto.DinosaurDTO;
import com.model.Dinosaur;
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
    public Dinosaur createDinosaur(@RequestPart("dinosaur") DinosaurDTO dinosaurDTO,
                                   @RequestPart("image") MultipartFile image) throws IOException {
        return parkManagentService.createDinosaur(dinosaurDTO, image);
    }

    @PutMapping("/dinosaurs/{id}")
    public Dinosaur updateDinosaur(@PathVariable("id") Long id,
                                   @RequestBody DinosaurDTO dinosaurDTO) {
        return parkManagentService.updateDinosaur(dinosaurDTO);
    }

    @DeleteMapping("/dinosaurs/{id}")
    public ResponseEntity<Void> deleteDinosaur(@PathVariable("id") Long id) {
        parkManagentService.deleteDinosaur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recintos")
    public List<Enclousure> getAllEnclosures() {
        return parkManagentService.getAllEnclosures();
    }

    @PostMapping("/recintos")
    public Enclousure createEnclosure(@RequestPart("recinto") Enclousure enclosureDTO,
                                      @RequestPart("image") MultipartFile image) throws IOException {
        return parkManagentService.createEnclosure(enclosureDTO, image);
    }

    @PutMapping("/recintos/{id}")
    public Enclousure updateEnclosure(@PathVariable("id") Long id,
                                      @RequestBody Enclousure enclousure) {
        return parkManagentService.updateEnclosure(enclousure);
    }

    @DeleteMapping("/recintos/{id}")
    public ResponseEntity<Void> deleteEnclosure(@PathVariable("id") Long id) {
        parkManagentService.deleteEnclosure(id);
        return ResponseEntity.noContent().build();
    }
}
