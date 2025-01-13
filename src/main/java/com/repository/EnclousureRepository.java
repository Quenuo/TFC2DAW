package com.repository;

import com.model.Enclousure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnclousureRepository extends JpaRepository<Enclousure,Long> {
    @Query("SELECT COUNT(d) FROM Dinosaur d WHERE d.enclosure.id = :enclosureId")
    Long countDinosaursById(@Param("enclosureId") Long enclosureId);

}
