package com.mapper;

import com.dto.DinosaurDTO;
import com.model.Dinosaur;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//esta clase la uso para mapear el dinosaurio a data tranfer objet(DinosaurDTO)
@Component
public class DinosaurMapper {

    public DinosaurDTO toDTO(Dinosaur dinosaur){
      DinosaurDTO  dinosaurDTO =new DinosaurDTO();
      dinosaurDTO.setId(dinosaur.getId());
      dinosaurDTO.setName(dinosaur.getName());
      dinosaurDTO.setScientificName(dinosaur.getScientificName());
      dinosaurDTO.setCost(dinosaur.getCost());
      dinosaurDTO.setImage(dinosaur.getImage());
      dinosaurDTO.setEnclosure(dinosaur.getEnclosure().getId());

      return dinosaurDTO;
    }

    public List<DinosaurDTO> toDTO(List<Dinosaur> dinosaurs){
        List<DinosaurDTO> dinosaurDTOS=new ArrayList<>();
        dinosaurs.forEach(dinosaur -> dinosaurDTOS.add(toDTO(dinosaur)));

        return dinosaurDTOS;
    }

}
