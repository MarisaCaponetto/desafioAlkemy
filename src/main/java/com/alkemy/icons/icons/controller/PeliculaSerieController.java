package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.PeliculaSerieBasicDTO;
import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.entity.GeneroEntity;
import com.alkemy.icons.icons.service.PeliculaSerieService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class PeliculaSerieController {
    
    
    private PeliculaSerieService peliculaSerieService;
    
    @Autowired
    public PeliculaSerieController(PeliculaSerieService peliculaSerieService){
        this.peliculaSerieService = peliculaSerieService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<PeliculaSerieBasicDTO>> gettAll(){
        List<PeliculaSerieBasicDTO> peliculas = this.peliculaSerieService.getAll();
        return ResponseEntity.ok(peliculas);
    }
    
    //Busqueda de peliculas por id
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaSerieDTO> getDetailsById(@PathVariable Long id){
        PeliculaSerieDTO pelicula = this.peliculaSerieService.getDetailsById(id);
        return ResponseEntity.ok(pelicula);
    }
    
    //Busqueda por filtro combinado
     @GetMapping
     public ResponseEntity<List<PeliculaSerieDTO>> getDetailsByFilters(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) GeneroEntity genre,
        @RequestParam(required = false, defaultValue = "ASC") String order
        ){
         List<PeliculaSerieDTO> peliculasSeries = this.peliculaSerieService.getByFilters(name, genre, order);
         return ResponseEntity.ok(peliculasSeries);
     }
     
     //Guardado Pelicula
     @PostMapping
     public ResponseEntity<PeliculaSerieDTO> save(@RequestBody PeliculaSerieDTO pelicula){
         PeliculaSerieDTO peliculaResult = peliculaSerieService.save(pelicula);
         return ResponseEntity.status(HttpStatus.CREATED).body(peliculaResult);
     }
     
     //Actualizacion
     @PutMapping("{id]")
     public ResponseEntity<PeliculaSerieDTO> update(@PathVariable Long id, @RequestBody PeliculaSerieDTO peliculaSerie){
         PeliculaSerieDTO peliculaResult = this.peliculaSerieService.update(id, peliculaSerie);
         return ResponseEntity.ok().body(peliculaResult);
     }
    //Eliminacion de Pelicula por Id
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
         this.peliculaSerieService.delete(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }
     
     @PostMapping("/{id}/personaje/{idPersonaje}")
     public ResponseEntity<Void> addPersonaje(@PathVariable Long id, @PathVariable Long idPersonaje){
         this.peliculaSerieService.addPersonaje(id, idPersonaje);
         return ResponseEntity.status(HttpStatus.CREATED).build();
     }
     
     //Remover personaje
     @DeleteMapping("/{id}/pelicula/{idPersonaje}")
     public ResponseEntity<Void> removePersonaje(@PathVariable Long id, @PathVariable Long idPersonaje){
         this.peliculaSerieService.removePersonaje(id, idPersonaje);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }
     
     
//    @GetMapping
//    public ResponseEntity<List<PeliculaSerieDTO>> getAll(){
//        List<PeliculaSerieDTO> peliculas = peliculaSerieService.getAllPeliculasSeries();
//        return ResponseEntity.ok().body(peliculas);
//    }
//
   
    
   
    
    
    
    
    
}
