package com.controller;

import com.model.Park;
import com.repository.ParkRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/park")
public class ParkController {
    private final ParkRepository parkRepository;

    @Autowired
    public ParkController(ParkRepository parkRepository){
        this.parkRepository=parkRepository;
    }

    @GetMapping("/status")
    public ResponseEntity<Park> getParkStatus(HttpServletRequest httpServletRequest){
        String userId= (String) httpServletRequest.getAttribute("userId");
        if(userId==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
        try{
            Optional<Park> parkOptional = parkRepository.findByUser_Id(Long.valueOf(userId));
            if (parkOptional.isPresent()) {
                return ResponseEntity.ok(parkOptional.get());
            } else {
                throw new RuntimeException("Park not founded for user with id:  " + userId);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update")
    public Park updatePark(@RequestBody Park park){


        try{
            Optional<Park> parkOptional = parkRepository.findByUser_Id(park.getUserId());
            if(parkOptional.isPresent()){
                System.out.println("Parque recibido desde parkcontroller upadate");
                Park oldPark=parkOptional.get();
                oldPark.setCoin(park.getCoin());
                oldPark.setEnclosures(park.getEnclosures()!= null ? park.getEnclosures() : oldPark.getEnclosures());
                oldPark.setName(park.getName() != null ? park.getName() : oldPark.getName());
                oldPark.setDinosaurList(park.getDinosaurList()!= null ? park.getDinosaurList() : oldPark.getDinosaurList());
                return parkRepository.save(oldPark);
            }else{
                throw new RuntimeException("Park not founded for user with id:  " + park.getUserId());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/create")
    public ResponseEntity<String> createParkName(@RequestBody Map<String, String> requestBody, HttpServletRequest httpServletRequest) {
        String userId = (String) httpServletRequest.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No user ID found in request.");
        }

        String parkName = requestBody.get("parkName");

        if (parkName == null || parkName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Park name is required.");
        }
        try {
            Optional<Park> newPark = parkRepository.findByUser_Id(Long.valueOf(userId));
            if(newPark.isPresent()){
                newPark.get().setName(parkName);
                parkRepository.save(newPark.get());
                return ResponseEntity.status(HttpStatus.CREATED).body("Park created successfully.");
            }else{
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found in database please log in again");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating park: " + e.getMessage());
        }
    }











}
