package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import org.springframework.stereotype.Component;

@Component
public class PeliculaSerieMapper {
    
    //Conversion de DTO a Entity
    public PeliculaSerieEntity peliculaSerieDTO2Entity(PeliculaSerieDTO dto){
        
        PeliculaSerieEntity peliculaSerieEntity = new PeliculaSerieEntity();
        
        peliculaSerieEntity.setImagen(dto.getImagen());
        peliculaSerieEntity.setTitulo(dto.getTitulo());
        peliculaSerieEntity.setFechaCreacion(dto.getFechaCreacion());
        peliculaSerieEntity.setCalificacion(dto.getCalificacion());
        peliculaSerieEntity.setPersonajes(dto.getPersonajes());
        
        return peliculaSerieEntity;
    }
    
    //Conversion de Entidad a DTO
    public PeliculaSerieDTO peliculaSerieEntity2DTO(PeliculaSerieEntity entity){
        
        PeliculaSerieDTO dto = new PeliculaSerieDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setCalificacion(entity.getCalificacion());
        dto.setPersonajes(entity.getPersonajes());
        dto.setGenero(entity.getGenero());
        dto.setGeneroId(entity.getGeneroId());
        
        return dto;
    }
    
}
