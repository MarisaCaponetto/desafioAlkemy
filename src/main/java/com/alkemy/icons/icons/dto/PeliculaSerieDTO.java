package com.alkemy.icons.icons.dto;

import com.alkemy.icons.icons.entity.GeneroEntity;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import com.alkemy.icons.icons.enumType.CalificacionEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PeliculaSerieDTO {
    
    private Long id;
    
    private String imagen;
    
    private String titulo;
    
    private Date fechaCreacion;
    
    private CalificacionEntity calificacion;
    
    private GeneroEntity genero;
    
    private Long generoId;
    
    private Set<PersonajeEntity> personajes = new HashSet<>();
    
}
