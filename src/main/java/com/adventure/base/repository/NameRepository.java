package com.adventure.base.repository;

import com.adventure.base.model.FirstName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NameRepository extends JpaRepository<FirstName, Integer> {

    @Query(value = "SELECT * FROM first_name ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    FirstName findRandomName();


    Optional<FirstName> findByFirstName(String name);
}
