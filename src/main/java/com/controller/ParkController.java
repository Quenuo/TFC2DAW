package com.controller;

import com.dto.ParkDTO;
import com.mapper.ParkMapper;
import com.model.Dinosaur;
import com.model.Enclousure;
import com.model.Park;
import com.services.ParkService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/park")
public class ParkController {
    private final ParkService parkService;
    private final ParkMapper parkMapper;

    @Autowired
    public ParkController(ParkService parkService, ParkMapper parkMapper){
        this.parkService=parkService;
        this.parkMapper = parkMapper;
    }

    @GetMapping("/status")
    public ResponseEntity<ParkDTO> getParkStatus(HttpServletRequest httpServletRequest){
        String userId= (String) httpServletRequest.getAttribute("userId");
        if(userId==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
        Optional<Park> parkOptional = parkService.getParkByUserId(Long.valueOf(userId));
        if (parkOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ParkDTO parkDTO = parkMapper.toDTO(parkOptional.get());
        return ResponseEntity.ok(parkDTO);
    }

    @PutMapping("/update")
    public Park updatePark(@RequestBody ParkDTO parkDTO){


        try{
            Optional<Park> parkOptional = parkService.getParkByUserId(parkDTO.getUserId());
            if(parkOptional.isPresent()){
                Park oldPark=parkOptional.get();
                oldPark.setCoin(parkDTO.getCoin());

                List<Enclousure> enclousureList=parkService.getEnclosuresByIds(parkDTO.getEnclosureIds());
                oldPark.setEnclosures(enclousureList);

                List<Dinosaur> dinosaurs=parkService.getDinosaursByIds(parkDTO.getDinosaurIds());
                oldPark.setDinosaurList(dinosaurs);

                oldPark.setName(parkDTO.getName() != null ? parkDTO.getName() : oldPark.getName());
                return parkService.savePark(oldPark);
            }else{
                throw new RuntimeException("Park not founded for user with id:  " + parkDTO.getUserId());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/create")
    public ResponseEntity<Map<String,String>> createParkName(@RequestBody Map<String, String> requestBody, HttpServletRequest httpServletRequest) {
        String userId = (String) httpServletRequest.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Unauthorized: No user ID found in request."));
        }

        String parkName = requestBody.get("parkName");

        if (parkName == null || parkName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message","Park name is required."));
        }
        try {
            Optional<Park> newPark = parkService.getParkByUserId(Long.valueOf(userId));
            if(newPark.isPresent()){
                newPark.get().setName(parkName);
                parkService.savePark(newPark.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","Park created successfully."));
            }else{
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","User not found in database please log in again"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","Error creating park: " + e.getMessage()));
        }
    }











}
