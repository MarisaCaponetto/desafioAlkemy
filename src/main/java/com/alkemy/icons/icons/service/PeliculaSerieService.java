package com.alkemy.icons.icons.service;



import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import java.util.List;



public interface PeliculaSerieService {
    
    //Metodo guardar
    public PeliculaSerieDTO save(PeliculaSerieDTO dto);
    
    List<PeliculaSerieDTO> getAllPeliculasSeries();
    
    void delete(Long id);
    
}
