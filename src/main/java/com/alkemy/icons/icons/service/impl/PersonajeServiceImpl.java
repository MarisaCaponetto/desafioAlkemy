package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.PersonajeBasicDTO;
import com.alkemy.icons.icons.dto.PersonajeDTO;
import com.alkemy.icons.icons.dto.PersonajeFiltersDTO;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.PersonajeMapper;
import com.alkemy.icons.icons.repository.PersonajeRepository;
import com.alkemy.icons.icons.repository.specifications.PersonajeSpecification;
import com.alkemy.icons.icons.service.PeliculaSerieService;
import com.alkemy.icons.icons.service.PersonajeService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements PersonajeService {
    
    //Mapper
    private PersonajeMapper personajeMapper;
    
    //Repository
    private PersonajeRepository personajeRepository;
    private PersonajeSpecification personajeSpecification;
    
    //Service
    PeliculaSerieService peliculaSerieService;
    
    @Autowired
    public PersonajeServiceImpl(
            PersonajeRepository personajeRespository,
            PersonajeSpecification personajeSpecification,
            PeliculaSerieService peliculaSerieService,
            PersonajeMapper personajeMapper){
        this.personajeSpecification = personajeSpecification;
        this.personajeRepository = personajeRepository;
        this.personajeMapper = personajeMapper;
        this.peliculaSerieService = peliculaSerieService;
    }
    //Busqueda
    @Override
    public PersonajeDTO getDetailsById(Long id) {
        Optional<PersonajeEntity> entity = this.personajeRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id personaje no valido!");
        }
        PersonajeDTO personajeDTO = this.personajeMapper.personajeEntity2DTO(entity.get(), true);
        return personajeDTO;
    }
    
    //Guardado
    public PersonajeDTO save(PersonajeDTO personajeDTO){
        PersonajeEntity entity = personajeMapper.personajeDTO2Entity(personajeDTO);
        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitySaved, false);
        System.out.println("GUARDAR PERSONAJE");
        return result;
    }
    //Actualizacion
    public PersonajeDTO update(Long id, PersonajeDTO personajeDTO){
        Optional<PersonajeEntity> entity = this.personajeRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("id personaje no valido!");
        }
        this.personajeMapper.personajeEntityRefreshValues(entity.get(), personajeDTO);
        PersonajeEntity entitySaved = this.personajeRepository.save(entity.get());
        PersonajeDTO result = this.personajeMapper.personajeEntity2DTO(entitySaved, false);
        return result;
    }
    //Listado con basic
    @Override
    public List<PersonajeBasicDTO> getAll(){
        List<PersonajeEntity> entities = this.personajeRepository.findAll();
        List<PersonajeBasicDTO> personajeBasicDTO = this.personajeMapper.personajeEntitySet2BasicDTOList(entities);
        return personajeBasicDTO;
    }
    
    
    //Listado
    @Override
    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> personajeEntities = personajeRepository.findAll();
        List<PersonajeDTO> result = personajeMapper.personajeEntitySet2DTOList(personajeEntities, true);
        //List<PersonajeDTO> result = personajeMapper.personajeEntityList2DTOList(personajeEntities);
        return result;
    }
    //Eliminacion por id
    @Override
    public void delete(Long id) {
        this.personajeRepository.deleteById(id);
    }
    
    //Busqueda por filtros
    @Override
    public List<PersonajeDTO> getByFilters(String name, String age, Set<Long> movies, String order) {
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name, age, movies, order);
        List<PersonajeEntity> entities = this.personajeRepository.findAll(this.personajeSpecification.getByFilters(filtersDTO));
        List<PersonajeDTO> dtos = this.personajeMapper.personajeEntitySet2DTOList(entities, true);//Consultar loadPaises: porque no lo toma sera por v8?
        return dtos;
    }
    
    //Agregar Pelicula
    public void addPelicula(Long id, Long idPelicula){
        PersonajeEntity entity = this.personajeRepository.getById(id);
        entity.getPeliculasSeries().size();
        PeliculaSerieEntity peliculaEntity = this.peliculaSerieService.getEntityById(idPelicula);
        entity.addPeliculaSerie(peliculaEntity);
        this.personajeRepository.save(entity);
    }
    
    //Eliminacion Pelicula
    public void removePelicula(Long id, Long idPelicula){
        PersonajeEntity entity = this.personajeRepository.getById(id);
        entity.getPeliculasSeries().size();
        PeliculaSerieEntity peliculaSerieEntity = this.peliculaSerieService.getEntityById(idPelicula);//Crear metodo en service de pelicula
        entity.removePeliculaSerie(peliculaSerieEntity); 
        this.personajeRepository.save(entity);
               
    }

    

   

   

   
    
}
