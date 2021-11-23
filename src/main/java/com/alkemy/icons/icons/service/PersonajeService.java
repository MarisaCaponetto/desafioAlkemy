package com.alkemy.icons.icons.service;


import com.alkemy.icons.icons.dto.PersonajeDTO;
import java.util.List;
import java.util.Set;



public interface PersonajeService {
    
    PersonajeDTO save(PersonajeDTO dto);
    
    List<PersonajeDTO> getAllPersonajes();
    
    //Eliminar
    void delete(Long id); 
    
    //Busqueda por filtros
    List<PersonajeDTO> getByFilters(String name, String age, Set<Long>movies, String order);
    
}
