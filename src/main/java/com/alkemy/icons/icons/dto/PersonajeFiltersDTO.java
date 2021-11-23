package com.alkemy.icons.icons.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeFiltersDTO {
    private String name;
    private String age;
    private Set<Long> movies;
    private String order;

    public PersonajeFiltersDTO(String name, String age, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.movies = movies;
        this.order = order;
    }
    
    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0; //Consultar str: "ASC"
    }
    
    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;//Consultar str: "DESC"
    }
    
    
    
}
