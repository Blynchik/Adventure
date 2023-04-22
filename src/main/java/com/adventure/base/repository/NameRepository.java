package com.adventure.base.repository;

import com.adventure.base.model.FirstName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface NameRepository extends JpaRepository<FirstName, Integer> {

    @Query("FROM FirstName WHERE id=:rand")
    FirstName findRandomName();
}
