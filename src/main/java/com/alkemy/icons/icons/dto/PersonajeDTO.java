package com.alkemy.icons.icons.dto;

import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeDTO {
    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    private List<PeliculaSerieEntity> peliculasSeries = new ArrayList<>(); 
    
    
}
