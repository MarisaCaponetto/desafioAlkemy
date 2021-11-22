package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.GeneroDTO;
import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.service.PeliculaSerieService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class PeliculaSerieController {
    
    @Autowired
    private PeliculaSerieService peliculaSerieService;
    
    @GetMapping
    public ResponseEntity<List<PeliculaSerieDTO>> getAll(){
        List<PeliculaSerieDTO> peliculas = peliculaSerieService.getAllPeliculasSeries();
        return ResponseEntity.ok().body(peliculas);
    }
    
   
    
    @PostMapping
    public ResponseEntity<PeliculaSerieDTO> save(@RequestBody PeliculaSerieDTO pelicula){
        //Guardar pelicula
        PeliculaSerieDTO peliculaGuardada = peliculaSerieService.save(pelicula);
        //responder 201
        //devolver la pelicula guardada
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }
    
    //Eliminacion de Pelicula o Serie por Id
    @DeleteMapping("/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        this.peliculaSerieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    
    
}
