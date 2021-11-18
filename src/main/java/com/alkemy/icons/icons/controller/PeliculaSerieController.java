package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.service.PeliculaSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class PeliculaSerieController {
    
    @Autowired
    private PeliculaSerieService peliculaSerieService;
    
    @PostMapping
    public ResponseEntity<PeliculaSerieDTO> save(@RequestBody PeliculaSerieDTO pelicula){
        //Guardar pelicula
        PeliculaSerieDTO peliculaGuardada = peliculaSerieService.save(pelicula);
        //responder 201
        //devolver la pelicula guardada
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }
    
    
}
