package com.alkemy.icons.icons.service;


import com.alkemy.icons.icons.dto.PersonajeBasicDTO;
import com.alkemy.icons.icons.dto.PersonajeDTO;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import java.util.List;
import java.util.Set;



public interface PersonajeService {
    
    //Busqueda por id
    PersonajeDTO getDetailsById(Long id);
    
    //Busqueda por id
    public PersonajeEntity getEntityById(Long id);
    
    //Listado personaje 
    List<PersonajeBasicDTO> getAll();
    
    //Busqueda por filtros
    List<PersonajeDTO> getByFilters(String name, String age, Set<Long>movies, String order);
    
    //Guardado    
    PersonajeDTO save(PersonajeDTO dto);
    
    //Listado     
    List<PersonajeDTO> getAllPersonajes();
    
    //Actualizar
    PersonajeDTO update(Long id, PersonajeDTO personaje);
    
    //Agregar Pelicula
    void addPelicula(Long id, Long idPelicula);
    
    //Eliminar Pelicula
    void removePelicula(Long id, Long idPelicula);
    
    //Eliminar Personaje
    void delete(Long id); 
    
    
    
    
    
    
}
