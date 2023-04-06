package com.adventure.base.repository;

import com.adventure.base.model.Adventurer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventurerRepository extends JpaRepository<Adventurer, Long> {
}
