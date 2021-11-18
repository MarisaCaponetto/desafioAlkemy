package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.PersonajeDTO;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonajeMapper {
    
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){
        PersonajeEntity personajeEntity = new PersonajeEntity();
        
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        personajeEntity.setPeliculasSeries(dto.getPeliculasSeries());
        
        return personajeEntity;
    }
    //Conversion a DTO
    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity){
        PersonajeDTO dto = new PersonajeDTO();
        
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        dto.setPeliculasSeries(entity.getPeliculasSeries());
        
        return dto;
    }
}
