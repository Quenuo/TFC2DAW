package com.repository;

import com.model.Park;
import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkRepository extends JpaRepository<Park,Long> {
    Optional<Park> findByUser_Id(Long userId);
    Park findByUser(User user);


}
