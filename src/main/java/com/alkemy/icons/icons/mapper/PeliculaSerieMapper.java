package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.PeliculaSerieBasicDTO;
import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.dto.PersonajeDTO;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeliculaSerieMapper {
    
    @Autowired
    PersonajeMapper personajeMapper;
    
    //Conversion de DTO a Entity
    public PeliculaSerieEntity peliculaSerieDTO2Entity(PeliculaSerieDTO dto){
        
        PeliculaSerieEntity peliculaSerieEntity = new PeliculaSerieEntity();
        
        peliculaSerieEntity.setImagen(dto.getImagen());
        peliculaSerieEntity.setTitulo(dto.getTitulo());
        peliculaSerieEntity.setFechaCreacion(
                this.string2LocalDate(dto.getFechaCreacion())
        );
        peliculaSerieEntity.setCalificacion(dto.getCalificacion());
       
        
        return peliculaSerieEntity;
    }
    
    //Conversion de Entidad a DTO
    
    public PeliculaSerieDTO peliculaSerieEntity2DTO(PeliculaSerieEntity entity, boolean loadPersonajes){
        
        PeliculaSerieDTO dto = new PeliculaSerieDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion().toString());
        dto.setCalificacion(entity.getCalificacion());
        if(loadPersonajes){
            List<PersonajeDTO> personajesDTO = this.personajeMapper.personajeEntitySet2DTOList(entity.getPersonajes(), loadPersonajes = false); //loadPeliculasSeries: false Consultar bucle
            dto.setPersonajes(personajesDTO);
        }      
        
        return dto;
    }
    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Consultar por el error (;)
        LocalDate date = LocalDate.parse(stringDate, formatter);
                 
        return date;
    }
    //ACTUALIZAR??
    public void peliculaSerieEntityRefreshValues(PeliculaSerieEntity entity, PeliculaSerieDTO peliculaSerieDTO){
        entity.setImagen(peliculaSerieDTO.getImagen());
        entity.setTitulo(peliculaSerieDTO.getTitulo());
        entity.setFechaCreacion(
                this.string2LocalDate(peliculaSerieDTO.getFechaCreacion()));
        entity.setCalificacion(peliculaSerieDTO.getCalificacion());
    }
    //
    public Set<PeliculaSerieEntity> peliculaSerieDTOList2EntityList(List<PeliculaSerieDTO> dtos){
        Set<PeliculaSerieEntity> entities = new HashSet<>();
        for(PeliculaSerieDTO aux : dtos){
            entities.add(this.peliculaSerieDTO2Entity(aux));
        }
        return entities;
    }
    //
    /**
     * @param entities(Set or List)
     * @param loadPersonajes
     * @param entities
     * 
     */
    public List<PeliculaSerieDTO> peliculaSerieEntitySetList2DTOList(Collection<PeliculaSerieEntity> entities, boolean loadPersonajes){
        List<PeliculaSerieDTO> dtos = new ArrayList<>();
        for(PeliculaSerieEntity aux : entities){
            dtos.add(this.peliculaSerieEntity2DTO(aux, loadPersonajes));
        }
        return dtos;
    }
    
   public List<PeliculaSerieBasicDTO> peliculaSerieEntitySet2BasicDTOList(Collection<PeliculaSerieEntity> entities){
       List<PeliculaSerieBasicDTO> dtos = new ArrayList<>();
       PeliculaSerieBasicDTO basicDTO;
       for(PeliculaSerieEntity aux : entities){
           basicDTO = new PeliculaSerieBasicDTO();
           basicDTO.setId(aux.getId());
           basicDTO.setImagen(aux.getImagen());
           basicDTO.setTitulo(aux.getTitulo());
           basicDTO.setFechaCreacion(aux.getFechaCreacion().toString());
           dtos.add(basicDTO);
       }
       return dtos;
   }
    
   
    //public List<PeliculaSerieDTO> peliculaSerieEntityList2DTOList(List<PeliculaSerieEntity> entities){
       // List<PeliculaSerieDTO> peliculaSerieDtos = new ArrayList<>();
       // for(PeliculaSerieEntity aux : entities){
           
       // }
       // return peliculaSerieDtos;
    //}
        
}
