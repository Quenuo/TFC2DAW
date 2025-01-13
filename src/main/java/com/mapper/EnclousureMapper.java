package com.mapper;

import com.dto.EnclousureDTO;
import com.model.Enclousure;
import com.repository.EnclousureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class EnclousureMapper {
    private final EnclousureRepository enclousureRepository;

    @Autowired
    public EnclousureMapper(EnclousureRepository enclousureRepository){
        this.enclousureRepository=enclousureRepository;
    }

    public EnclousureDTO toDTO(Enclousure enclousure){
        EnclousureDTO  enclousureDTO=new EnclousureDTO();
        enclousureDTO.setName(enclousure.getName());
        enclousureDTO.setDescription(enclousure.getDescription());
        enclousureDTO.setCost(enclousure.getCost());
        enclousureDTO.setId(enclousure.getId());
        enclousureDTO.setImage(enclousure.getImage());
        enclousureDTO.setDinosaurCount(enclousureRepository.countDinosaursById(enclousure.getId()));
        return enclousureDTO;

    }

    public List<EnclousureDTO> toDTO(List<Enclousure> enclousure){
        List<EnclousureDTO>  enclosureDTOs=new ArrayList<>();
        enclousure.forEach(enclousure1 -> enclosureDTOs.add(toDTO(enclousure1)));
        return enclosureDTOs;
    }
}

