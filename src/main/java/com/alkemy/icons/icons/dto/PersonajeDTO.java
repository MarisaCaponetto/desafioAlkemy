package com.alkemy.icons.icons.dto;

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
    private String edad;
    private Double peso;
    private String historia;
    private List<PeliculaSerieDTO> peliculasSeries = new ArrayList<>(); 
    
    
}
