package com.services;

import com.dto.DinosaurDTO;
import com.mapper.DinosaurMapper;
import com.model.Dinosaur;
import com.model.Enclousure;
import com.repository.DinosaurRepository;
import com.repository.EnclousureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ParkManagentService {
    private final EnclousureRepository enclousureRepository;
    private final DinosaurRepository dinosaurRepository;
    private final FileStorageService fileStorageService;
    private final DinosaurMapper dinosaurMapper;

    @Autowired
    public ParkManagentService(EnclousureRepository enclousureRepository,DinosaurRepository dinosaurRepository,FileStorageService fileStorageService,DinosaurMapper dinosaurMapper){
        this.enclousureRepository=enclousureRepository;
        this.dinosaurRepository=dinosaurRepository;
        this.fileStorageService=fileStorageService;
        this.dinosaurMapper=dinosaurMapper;
    }

    public List<DinosaurDTO> getAllDinosaurs() {
        return dinosaurMapper.toDTO(dinosaurRepository.findAll());
    }

    public Dinosaur createDinosaur(DinosaurDTO dinosaurDTO, MultipartFile image) throws IOException {
        String imagePath = fileStorageService.saveFile(image, "dinosaur");
        Dinosaur dinosaur = new Dinosaur();
        dinosaur.setName(dinosaurDTO.getName());
        dinosaur.setScientificName(dinosaurDTO.getScientificName());
        dinosaur.setCost(dinosaurDTO.getCost());
        dinosaur.setImage(imagePath);
        Optional<Enclousure> enclousure=enclousureRepository.findById(dinosaurDTO.getEnclosure());
        if(enclousure.isPresent()){
            dinosaur.setEnclosure(enclousure.get());
            return dinosaurRepository.save(dinosaur);
        }else{
            throw  new RuntimeException("Enclosure not found");
        }


    }

    public Dinosaur updateDinosaur( DinosaurDTO dinosaurDTO) {
        return dinosaurRepository.findById(dinosaurDTO.getId())
                .map(dinosaur -> {
                    dinosaur.setName(dinosaurDTO.getName());
                    dinosaur.setScientificName(dinosaurDTO.getScientificName());
                    dinosaur.setCost(dinosaurDTO.getCost());
                    Optional<Enclousure> enclousure=enclousureRepository.findById(dinosaurDTO.getEnclosure());
                    if(enclousure.isPresent()){
                        dinosaur.setEnclosure(enclousure.get());
                        return dinosaurRepository.save(dinosaur);
                    }else{
                        throw  new RuntimeException("Enclosure not found");
                    }
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dinosaur not found"));
    }

    public void deleteDinosaur(Long id) {
        dinosaurRepository.deleteById(id);
    }


    public List<Enclousure> getAllEnclosures() {
        return enclousureRepository.findAll();
    }

    public Enclousure createEnclosure(Enclousure enclosure, MultipartFile image) throws IOException {
        String imagePath = fileStorageService.saveFile(image, "enclosure");
        enclosure.setImage(imagePath);
        return enclousureRepository.save(enclosure);
    }

    public Enclousure updateEnclosure(Enclousure enclousure) {
        return enclousureRepository.findById(enclousure.getId())
                .map(enclosure -> {
                    enclosure.setName(enclousure.getName());
                    enclosure.setDescription(enclousure.getDescription());
                    enclosure.setCost(enclousure.getCost());
                    return enclousureRepository.save(enclosure);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enclosure not found"));
    }

    public void deleteEnclosure(Long id) {
        if (dinosaurRepository.existsByEnclosureId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete enclosure with dinosaurs");
        }
        enclousureRepository.deleteById(id);
    }
}
