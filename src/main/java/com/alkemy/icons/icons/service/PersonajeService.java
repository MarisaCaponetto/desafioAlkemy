package com.alkemy.icons.icons.service;


import com.alkemy.icons.icons.dto.PersonajeDTO;
import java.util.List;



public interface PersonajeService {
    
    PersonajeDTO save(PersonajeDTO dto);
    
    List<PersonajeDTO> getAllPersonajes();
    
    void delete(Long id); 
    
}
