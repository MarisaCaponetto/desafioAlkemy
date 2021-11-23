package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerieEntity, Long>, JpaSpecificationExecutor<PeliculaSerieEntity> {
    
    List<PeliculaSerieEntity> findAll(Specification<PeliculaSerieEntity> spec);
}
