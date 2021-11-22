package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.PersonajeDTO;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import com.alkemy.icons.icons.mapper.PersonajeMapper;
import com.alkemy.icons.icons.repository.PersonajeRepository;
import com.alkemy.icons.icons.service.PersonajeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements PersonajeService {
    
    @Autowired
    private PersonajeMapper personajeMapper;
    
    @Autowired
    private PersonajeRepository personajeRepository;
    
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
        List<PersonajeDTO> result = personajeMapper.personajeEntityList2DTOList(personajeEntities, true);
        //List<PersonajeDTO> result = personajeMapper.personajeEntityList2DTOList(personajeEntities);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.personajeRepository.deleteById(id);
    }
    
}
