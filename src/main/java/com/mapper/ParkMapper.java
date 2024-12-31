package com.mapper;

import com.dto.ParkDTO;
import com.model.Dinosaur;
import com.model.Enclousure;
import com.model.Park;
import org.springframework.stereotype.Component;

//esta clase la uso para mapear el park a data tranfer objet(ParkDTO)
@Component
public class ParkMapper {


    public ParkDTO toDTO(Park park) {
        ParkDTO parkDTO = new ParkDTO();
        parkDTO.setId(park.getId());
        parkDTO.setName(park.getName());
        parkDTO.setCoin(park.getCoin());
        parkDTO.setUserId(park.getUserId());
        parkDTO.setEnclosureIds(
                park.getEnclosures().stream().map(Enclousure::getId).toList()
        );
        parkDTO.setDinosaurIds(
                park.getDinosaurList().stream().map(Dinosaur::getId).toList()
        );

        return parkDTO;
    }
}
