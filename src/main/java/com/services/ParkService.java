package com.services;

import com.model.Dinosaur;
import com.model.Enclousure;
import com.model.Park;
import com.repository.DinosaurRepository;
import com.repository.EnclousureRepository;
import com.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkService {
    private final EnclousureRepository enclousureRepository;
    private final DinosaurRepository dinosaurRepository;
    private final ParkRepository parkRepository;

    @Autowired
    public ParkService(EnclousureRepository enclousureRepository,DinosaurRepository dinosaurRepository,ParkRepository parkRepository){
        this.enclousureRepository=enclousureRepository;
        this.dinosaurRepository=dinosaurRepository;
        this.parkRepository=parkRepository;
    }

    public List<Enclousure> getEnclosuresByIds(List<Long> ids) {
        return ids != null && !ids.isEmpty() ? enclousureRepository.findAllById(ids) : null;
    }

    public List<Dinosaur>  getDinosaursByIds(List<Long> ids) {
        return ids != null && !ids.isEmpty() ? dinosaurRepository.findAllById(ids) : null;
    }

    public Optional<Park> getParkByUserId(Long userId) {
        return parkRepository.findByUser_Id(userId);
    }

    public Park savePark(Park park){
        return parkRepository.save(park);
    }
}
