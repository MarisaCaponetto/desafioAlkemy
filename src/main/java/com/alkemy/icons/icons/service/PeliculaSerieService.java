package com.alkemy.icons.icons.service;



import com.alkemy.icons.icons.dto.PeliculaSerieBasicDTO;
import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.entity.GeneroEntity;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import java.util.List;




public interface PeliculaSerieService {
    
    //Busqueda por id
    PeliculaSerieDTO getDetailsById(Long id); 
    
    //Busqueda por id
    public PeliculaSerieEntity getEntityById(Long id);
    
    //Listado peliculas
    List<PeliculaSerieBasicDTO> getAll();
    
    //Busqueda por filtros combinados
    List<PeliculaSerieDTO> getByFilters(String nombre, GeneroEntity genre, String order);
        
    //Metodo guardar
    public PeliculaSerieDTO save(PeliculaSerieDTO dto);
    
    //Listado    
    List<PeliculaSerieDTO> getAllPeliculasSeries();
    
    //Actualizar
    PeliculaSerieDTO update(Long id, PeliculaSerieDTO pelicula); 
    
    //Agregar Personaje
    void addPersonaje(Long id, Long idPersonaje);
    
    //Eliminar Personaje
    void removePersonaje(Long id, Long idPelicula);
    
    //Metodo eliminar
    void delete(Long id);
    
    
    
      



}
    

