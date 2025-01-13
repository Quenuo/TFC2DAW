package com.services;

import com.dto.DinosaurDTO;
import com.dto.EnclousureDTO;
import com.mapper.DinosaurMapper;
import com.mapper.EnclousureMapper;
import com.model.Dinosaur;
import com.model.Emergency;
import com.model.Enclousure;
import com.repository.DinosaurRepository;
import com.repository.EmergencyRepository;
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
    private final EnclousureMapper enclousureMapper;
    private final EmergencyRepository emergencyRepository;

    @Autowired
    public ParkManagentService(EnclousureRepository enclousureRepository,DinosaurRepository dinosaurRepository,FileStorageService fileStorageService,DinosaurMapper dinosaurMapper,EnclousureMapper enclousureMapper,EmergencyRepository emergencyRepository){
        this.enclousureRepository=enclousureRepository;
        this.dinosaurRepository=dinosaurRepository;
        this.fileStorageService=fileStorageService;
        this.dinosaurMapper=dinosaurMapper;
        this.enclousureMapper=enclousureMapper;
        this.emergencyRepository=emergencyRepository;
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

    public Dinosaur updateDinosaur(Long id, DinosaurDTO dinosaurDTO,MultipartFile image) {
        return dinosaurRepository.findById(id)
                .map(dinosaur -> {
                    dinosaur.setName(dinosaurDTO.getName());
                    dinosaur.setScientificName(dinosaurDTO.getScientificName());
                    dinosaur.setCost(dinosaurDTO.getCost());
                    Optional<Enclousure> enclousure=enclousureRepository.findById(dinosaurDTO.getEnclosure());

                    if (image != null && !image.isEmpty()) {
                        try {
                            String imagePath = fileStorageService.saveFile(image, "dinosaur");
                            dinosaur.setImage(imagePath);
                        } catch (IOException e) {
                            throw new RuntimeException("Error saving image", e);
                        }
                    }
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

    public List<EnclousureDTO> getAllEnclosures() {
        return enclousureMapper.toDTO(enclousureRepository.findAll());

    }

    public Enclousure createEnclosure(EnclousureDTO enclosureDTO, MultipartFile image) throws IOException {
        String imagePath = fileStorageService.saveFile(image, "enclosure");
        Enclousure enclousure = new Enclousure();
        enclousure.setName(enclosureDTO.getName());
        enclousure.setDescription(enclosureDTO.getDescription());
        enclousure.setCost(enclosureDTO.getCost());
        enclousure.setImage(imagePath);
        return enclousureRepository.save(enclousure);
    }

    public Enclousure updateEnclosure(Long id,EnclousureDTO enclousureDTO,MultipartFile image) {
        return enclousureRepository.findById(id)
                .map(enclosure -> {
                    enclosure.setName(enclousureDTO.getName());
                    enclosure.setDescription(enclousureDTO.getDescription());
                    enclosure.setCost(enclousureDTO.getCost());
                    if (image != null && !image.isEmpty()) {
                        try {
                            String imagePath = fileStorageService.saveFile(image, "enclosure");
                            enclosure.setImage(imagePath);
                        } catch (IOException e) {
                            throw new RuntimeException("Error saving image", e);
                        }
                    }
                    return enclousureRepository.save(enclosure);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enclosure not found"));
    }

    public void deleteEnclosure(Long id) {
        if (dinosaurRepository.existsByEnclosureId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete enclosure with dinosaurs");
        }
        enclousureRepository.deleteById(id);
    }

    public List<Emergency> getAllEmergencies() {
        return emergencyRepository.findAll();

    }

    public Emergency createEmergency(Emergency emergency, MultipartFile image) throws IOException {
        String imagePath = fileStorageService.saveFile(image, "emergency");
        Emergency emergency1 = new Emergency();
        emergency1.setName(emergency.getName());
        emergency1.setCoinLost(emergency.getCoinLost());
        emergency1.setImage(imagePath);
        return emergencyRepository.save(emergency1);
    }

    public Emergency updateEmergency(Long id,Emergency emergency,MultipartFile image) {
        return emergencyRepository.findById(id)
                .map(emergency1 -> {
                    emergency1.setName(emergency.getName());
                    emergency1.setCoinLost(emergency.getCoinLost());
                    if (image != null && !image.isEmpty()) {
                        try {
                            String imagePath = fileStorageService.saveFile(image, "emergency");
                            emergency1.setImage(imagePath);
                        } catch (IOException e) {
                            throw new RuntimeException("Error saving image", e);
                        }
                    }
                    return emergencyRepository.save(emergency1);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emergency not found"));
    }

    public void deleteEmergency(Long id) {
        emergencyRepository.deleteById(id);
    }
}
