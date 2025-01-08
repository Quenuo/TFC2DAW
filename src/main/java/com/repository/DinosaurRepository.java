package com.repository;

import com.model.Dinosaur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinosaurRepository extends JpaRepository<Dinosaur,Long> {
    boolean existsByEnclosureId(Long enclosureId);

}
