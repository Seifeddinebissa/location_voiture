package com.example.gestionreclamation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionreclamation.entities.ReclamationEntity;

public interface ReclamationRepository extends JpaRepository<ReclamationEntity, Long> {
}
