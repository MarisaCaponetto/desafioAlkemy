package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.PersonajeBasicDTO;
import com.alkemy.icons.icons.dto.PersonajeDTO;
import com.alkemy.icons.icons.service.PersonajeService;
import java.util.List;
import java.util.Set;
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
@RequestMapping("characters")
public class PersonajeController {
    
    
    private PersonajeService personajeService;
    
    @Autowired
    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<PersonajeBasicDTO>> getAll(){
        List<PersonajeBasicDTO> personajes = this.personajeService.getAll(); //Metodo getAll en personajeService
        return ResponseEntity.ok(personajes);
    }
    
    //Busqueda personaje por id
    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id) {
        PersonajeDTO personaje = this.personajeService.getDetailsById(id);
        return ResponseEntity.ok(personaje);
    }
    
  
    
//  @GetMapping
//  public ResponseEntity<List<PersonajeDTO>> getAll(){
//      List<PersonajeDTO> personajes = personajeService.getAllPersonajes();
//      return ResponseEntity.ok().body(personajes);
//  }
    
     //Busqueda por filtro combinado
    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getDetailsByFilters(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String age,
        @RequestParam(required = false) Set<Long> movies,
        @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<PersonajeDTO> personajes = this.personajeService.getByFilters(name, age, movies, order);
        return ResponseEntity.ok(personajes);
        
    }
    
    
    //Guardado Personaje
    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){
        PersonajeDTO personajeResult = personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeResult);
    }
    
   //Actualizacion 
    @PutMapping("{id}")
    public ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @RequestBody PersonajeDTO personaje){
        PersonajeDTO personajeResult = this.personajeService.update(id, personaje); 
        return ResponseEntity.ok().body(personajeResult);
    }
    
       
    //Eliminacion de Personaje por Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    //
    
    @PostMapping("/{id}/pelicula/{idPelicula}")
    public ResponseEntity<Void> addPelicula(@PathVariable Long id, @PathVariable Long idPelicula){
        this.personajeService.addPelicula(id, idPelicula); 
        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }
    
    @DeleteMapping("/{id}/pelicula/{idPelicula}")
    public ResponseEntity<Void> removePelicula(@PathVariable Long id, @PathVariable Long idPelicula){
        this.personajeService.removePelicula(id, idPelicula);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
