package com.alkemy.icons.icons.service;



import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.entity.GeneroEntity;
import java.util.List;
import java.util.Set;



public interface PeliculaSerieService {
    
    //Metodo guardar
    public PeliculaSerieDTO save(PeliculaSerieDTO dto);
    
    List<PeliculaSerieDTO> getAllPeliculasSeries();
    
    //Metodo eliminar
    void delete(Long id);
    
    //Busqueda por filtros combinados
    List<PeliculaSerieDTO> getByFilters(String nombre, GeneroEntity genre, String order);
    
}
