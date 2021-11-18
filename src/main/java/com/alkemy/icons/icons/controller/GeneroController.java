package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.GeneroDTO;
import com.alkemy.icons.icons.service.GeneroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//Los controladores no manejan logica de guardado. Reciben una solicitud, y devuelven una respuesta.
@RestController
@RequestMapping("genres")
public class GeneroController {
    
    @Autowired 
    private GeneroService generoService;
    
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> generos = this.generoService.getAllGeneros();
        return ResponseEntity.ok().body(generos);
    }
    
    
    //Endpoint por cada uno, tenemos un verbo: guardado-POST
    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){
        //Guardar genero
        GeneroDTO generoGuardado = generoService.save(genero);
        //201, genero guardado
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }
    
}
