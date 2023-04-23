package com.adventure.base.repository;

import com.adventure.base.model.LastName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurnameRepository extends JpaRepository<LastName, Integer> {

    @Query(value = "SELECT * FROM last_name ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    LastName findRandomName();


    Optional<LastName> findByLastName(String surname);
}