package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerieEntity, Long> {
    
}
