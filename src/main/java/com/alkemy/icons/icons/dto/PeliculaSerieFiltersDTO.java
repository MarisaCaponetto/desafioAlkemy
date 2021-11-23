package com.alkemy.icons.icons.dto;

import com.alkemy.icons.icons.entity.GeneroEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaSerieFiltersDTO {
    
    private String name;
    private GeneroEntity genero;
    private String order;

    public PeliculaSerieFiltersDTO(String name, GeneroEntity genero, String order) {
        this.name = name;
        this.genero = genero;
        this.order = order;
    }
    
    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0; //Consultar str: "ASC"
    }
    
    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;//Consultar str: "DESC"
    }
    
    
}
