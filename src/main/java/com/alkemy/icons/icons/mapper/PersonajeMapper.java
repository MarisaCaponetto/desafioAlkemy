package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.dto.PersonajeBasicDTO;
import com.alkemy.icons.icons.dto.PersonajeDTO;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonajeMapper {
    
    @Autowired
    PeliculaSerieMapper peliculaSerieMapper;
    
    //Conversion de DTO a Entidad
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){
        PersonajeEntity personajeEntity = new PersonajeEntity();
        
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
               
        return personajeEntity;
    }
    //Conversion de Entidad a DTO
    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadPeliculas){
        PersonajeDTO dto = new PersonajeDTO();
        
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        if(loadPeliculas){
            List<PeliculaSerieDTO> peliculasDTO = this.peliculaSerieMapper.peliculaSerieEntitySetList2DTOList(entity.getPeliculasSeries(), loadPeliculas = false);
            //List<PeliculaSerieDTO> peliculasDTO = this.peliculaSerieMapper.peliculaSerieEntitySet2DTOList(entity.getPeliculasSeries(), loadPeliculas = false); //loadPersonajes: false Consultar bucle
            dto.setPeliculasSeries(peliculasDTO);
       }
                
        return dto;
    }
    
    //ACTUALIZAR???
    public void personajeEntityRefreshValues(PersonajeEntity entity, PersonajeDTO personajeDTO){
        entity.setImagen(personajeDTO.getNombre());
        entity.setNombre(personajeDTO.getNombre());
        entity.setEdad(personajeDTO.getEdad());
        entity.setPeso(personajeDTO.getPeso());
        entity.setHistoria(personajeDTO.getHistoria());
    }
    //
    public Set<PersonajeEntity> personajeDTOList2Entity(List<PersonajeDTO> dtos){
        Set<PersonajeEntity> entities = new HashSet<>();
        for(PersonajeDTO aux : dtos){
            entities.add(this.personajeDTO2Entity(aux));
        }
        return entities;
    }
    //
    /**
     *@param entities (Set or List)
     *@param loadPeliculas
     * 
     */
    public List<PersonajeDTO> personajeEntitySet2DTOList(Collection<PersonajeEntity> entities, boolean loadPeliculas){
        List<PersonajeDTO> dtos = new ArrayList<>();
        for(PersonajeEntity aux: entities){
            dtos.add(this.personajeEntity2DTO(aux, loadPeliculas));
        }
        return dtos;
    }
    
    public List<PersonajeBasicDTO> personajeEntitySet2BasicDTOList(Collection<PersonajeEntity> entities){
        List<PersonajeBasicDTO> dtos = new ArrayList<>();
        PersonajeBasicDTO basicDTO;
        for(PersonajeEntity aux: entities){
            basicDTO = new PersonajeBasicDTO();
            basicDTO.setId(aux.getId());
            basicDTO.setImagen(aux.getImagen());
            basicDTO.setNombre(aux.getNombre());
            dtos.add(basicDTO);
        }
        return dtos;
    }
    
    
    //public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> personajeEntities){
        //List<PersonajeDTO> personajeDtos = new ArrayList<>();
        //for(PersonajeEntity aux: personajeEntities){
            
        }
       // return personajeDtos;
    //}

