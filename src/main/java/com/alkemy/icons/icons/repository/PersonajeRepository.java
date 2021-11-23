package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entity.PersonajeEntity;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity, Long>, JpaSpecificationExecutor<PersonajeEntity> {
    
    List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);
}
