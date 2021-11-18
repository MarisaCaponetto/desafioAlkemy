package com.alkemy.icons.icons.entity;

import com.alkemy.icons.icons.enumType.CalificacionEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="pelicula_serie")
@Getter
@Setter
public class PeliculaSerieEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String imagen;
    
    private String titulo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_creacion")
    private Date fechaCreacion;
    
    @Enumerated(EnumType.STRING)
    private CalificacionEntity calificacion;
    //FetchType.EAGER: inicializacion de tipo temprana, cuando pido un dato de pelicula o serie, viene con su genero
    //CascadeType.ALL: todas las operaciones de la pelicula, recaen sobre el genero
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //Indica como joinear esta tabla con la entidad Genero, a traves del atributo genero_id
    //Se utiliza cuando se lista pais
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;
    
    //Define la columna generoId en la base de datos bajo el nombre de genero_id. 
    //Se utiliza en la creacion o actualizacion.
    @Column(name = "genero_id", nullable = false)  
    private Long generoId;
    //Pelicula es la duenia de la relacion. Tiene sentido crear una pelicula con sus personajes, por ello, la 
    //definicion esta del lado de la pelicula.
    @ManyToMany(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JoinTable(
        name = "personaje_pelicula",
        joinColumns = @JoinColumn(name = "pelicula_id"),
        inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private Set<PersonajeEntity> personajes = new HashSet<>();
    
}
