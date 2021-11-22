package com.alkemy.icons.icons.dto;

import com.alkemy.icons.icons.entity.GeneroEntity;
import com.alkemy.icons.icons.enumType.CalificacionEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PeliculaSerieDTO {
    
    private Long id;
    private String imagen;    
    private String titulo;    
    private String fechaCreacion;    
    private CalificacionEntity calificacion;    
    private GeneroEntity genero;          
    private List<PersonajeDTO> personajes = new ArrayList<>();
    
}
