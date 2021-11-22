package com.alkemy.icons.icons.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name="personaje")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")

public class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    
    private boolean deleted = Boolean.FALSE;
    
    //Cuando creo un personaje, no puedo pasar una lista de peliculas. 
    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaSerieEntity> peliculasSeries = new ArrayList<>(); 
    
    //Add and remove peliculas
    public void addPeliculaSerie(PeliculaSerieEntity peliculaSerie){
        this.peliculasSeries.add(peliculaSerie);
    }
    
    public void removePeliculaSerie(PeliculaSerieEntity peliculaSerie){
       this.peliculasSeries.remove(peliculaSerie);
    }
}
