package com.alkemy.icons.icons.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="genero")
@Getter
@Setter
public class GeneroEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    private String nombre;
    
    private String imagen;
    
    @OneToMany(mappedBy = "genero")
    private Set<PeliculaSerieEntity> peliculas = new HashSet<>();
   
    
}
