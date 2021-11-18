package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.GeneroDTO;
import java.util.List;

public interface GeneroService {
    
    //Metodo guardar
    public GeneroDTO save(GeneroDTO dto);
    
    List<GeneroDTO> getAllGeneros();
   
}
