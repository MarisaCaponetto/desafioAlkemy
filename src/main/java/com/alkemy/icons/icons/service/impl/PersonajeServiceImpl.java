package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.PersonajeDTO;
import com.alkemy.icons.icons.dto.PersonajeFiltersDTO;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.PersonajeMapper;
import com.alkemy.icons.icons.repository.PersonajeRepository;
import com.alkemy.icons.icons.repository.specifications.PersonajeSpecification;
import com.alkemy.icons.icons.service.PeliculaSerieService;
import com.alkemy.icons.icons.service.PersonajeService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements PersonajeService {
    
    //Mapper
    private PersonajeMapper personajeMapper;
    
    //Repository
    private PersonajeRepository personajeRepository;
    private PersonajeSpecification personajeSpecification;
    
    //Service
    PeliculaSerieService peliculaSerieService;
    
    @Autowired
    public PersonajeServiceImpl(
            PersonajeRepository personajeRespository,
            PersonajeSpecification personajeSpecification,
            PeliculaSerieService peliculaSerieService,
            PersonajeMapper personajeMapper){
        this.personajeRepository = personajeRepository;
        this.personajeMapper = personajeMapper;
        this.peliculaSerieService = peliculaSerieService;
    }
    
    
    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity entity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitySaved, true);
        //PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitySaved);
        System.out.println("GUARDAR PERSONAJE");
        return result;
    }

    @Override
    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> personajeEntities = personajeRepository.findAll();
        List<PersonajeDTO> result = personajeMapper.personajeEntitySet2DTOList(personajeEntities, true);
        //List<PersonajeDTO> result = personajeMapper.personajeEntityList2DTOList(personajeEntities);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.personajeRepository.deleteById(id);
    }

    @Override
    public List<PersonajeDTO> getByFilters(String name, String age, Set<Long> movies, String order) {
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name, age, movies, order);
        List<PersonajeEntity> entities = this.personajeRepository.findAll(this.personajeSpecification.getByFilters(filtersDTO));
        List<PersonajeDTO> dtos = this.personajeMapper.personajeEntitySet2DTOList(entities, true);//Consultar loadPaises: porque no lo toma sera por v8?
        return dtos;
    }

    @Override
    public PersonajeDTO getDetailsById(Long id) {
        Optional<PersonajeEntity> entity = this.personajeRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id personaje no valido!");
        }
        PersonajeDTO personajeDTO = this.personajeMapper.personajeEntity2DTO(entity.get(), true);
        return personajeDTO;
    }

   

   

   
    
}
