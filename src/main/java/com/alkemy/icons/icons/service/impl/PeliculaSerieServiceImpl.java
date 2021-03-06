package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.PeliculaSerieBasicDTO;
import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.dto.PeliculaSerieFiltersDTO;
import com.alkemy.icons.icons.entity.GeneroEntity;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.PeliculaSerieMapper;
import com.alkemy.icons.icons.repository.PeliculaSerieRepository;
import com.alkemy.icons.icons.repository.specifications.PeliculaSerieSpecification;
import com.alkemy.icons.icons.service.PeliculaSerieService;
import com.alkemy.icons.icons.service.PersonajeService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaSerieServiceImpl implements PeliculaSerieService {
    
    //Mapper
    private PeliculaSerieMapper peliculaSerieMapper;
    
    //Repository
    private PeliculaSerieRepository peliculaSerieRepository;
    private PeliculaSerieSpecification peliculaSerieSpecification;
    
    //Service
    PersonajeService personajeService;
    
    @Autowired
    public PeliculaSerieServiceImpl(
            PeliculaSerieRepository peliculaSerieRepository,
            PeliculaSerieSpecification peliculaSerieSpecification,
            PersonajeService personajeService,
            PeliculaSerieMapper peliculaSerieMapper){
        this.peliculaSerieRepository = peliculaSerieRepository;
        this.peliculaSerieSpecification = peliculaSerieSpecification;
        this.personajeService = personajeService;
        this.peliculaSerieMapper = peliculaSerieMapper;
    }
     
    //Busqueda
    @Override
    public PeliculaSerieDTO getDetailsById(Long id) {
        Optional<PeliculaSerieEntity> entity = this.peliculaSerieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id pelicula no valido!");
        }
        PeliculaSerieDTO peliculaSerieDTO = this.peliculaSerieMapper.peliculaSerieEntity2DTO(entity.get(), true);
        return peliculaSerieDTO;
    }
    
    //Busqueda
    @Override
    public PeliculaSerieEntity getEntityById(Long id){
        Optional<PeliculaSerieEntity> entity = this.peliculaSerieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id pelicula/serie no valido!");
        }
        PeliculaSerieEntity peliculaSerieEntity = this.peliculaSerieRepository.getById(id);
        return peliculaSerieEntity;
    }
    
    //Guardar Pelicula
    public PeliculaSerieDTO save(PeliculaSerieDTO dto){
        //Conversion a entity
        PeliculaSerieEntity entity = peliculaSerieMapper.peliculaSerieDTO2Entity(dto);
        //Guardado
        PeliculaSerieEntity entitySaved = peliculaSerieRepository.save(entity);
        //Conversion a DTO
        PeliculaSerieDTO result = peliculaSerieMapper.peliculaSerieEntity2DTO(entitySaved, false);
        //PeliculaSerieDTO result = peliculaSerieMapper.peliculaSerieEntity2DTO(entitySaved);
        System.out.println("GUARDAR PELICULA O SERIE");
        return result;
    }
    
    //Actualizacion
    public PeliculaSerieDTO update(Long id, PeliculaSerieDTO peliculaDTO){
        Optional<PeliculaSerieEntity> entity = this.peliculaSerieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("id pelicula no valido!"); 
        }
        this.peliculaSerieMapper.peliculaSerieEntityRefreshValues(entity.get(), peliculaDTO);
        PeliculaSerieEntity entitySaved = this.peliculaSerieRepository.save(entity.get());
        PeliculaSerieDTO result = this.peliculaSerieMapper.peliculaSerieEntity2DTO(entitySaved, false);
        return result;
    }
    
    //Listado basic
    @Override
    public List<PeliculaSerieBasicDTO> getAll(){
        List<PeliculaSerieEntity> entities = this.peliculaSerieRepository.findAll();
        List<PeliculaSerieBasicDTO> peliculaSerieBasicDTO = this.peliculaSerieMapper.peliculaSerieEntitySet2BasicDTOList(entities);
        return peliculaSerieBasicDTO;
    }

    @Override
    public List<PeliculaSerieDTO> getAllPeliculasSeries() {
        List<PeliculaSerieEntity> peliculaSerieEntities = peliculaSerieRepository.findAll();
        List<PeliculaSerieDTO> result = peliculaSerieMapper.peliculaSerieEntitySet2DTOList(peliculaSerieEntities, true);
        
        //List<PeliculaSerieDTO> result = peliculaSerieMapper.peliculaSerieEntityList2DTOList(peliculaSerieEntities);
        return result;
    }
    
    //Eliminacion por id
    @Override
    public void delete(Long id) {
        this.peliculaSerieRepository.deleteById(id);
    }
    
    //Busqueda por filtros
    @Override
    public List<PeliculaSerieDTO> getByFilters(String name, GeneroEntity genre, String order) {
        PeliculaSerieFiltersDTO filtersDTO = new PeliculaSerieFiltersDTO(name, genre, order);
        List<PeliculaSerieEntity> entities = this.peliculaSerieRepository.findAll(this.peliculaSerieSpecification.getByFilters(filtersDTO));
        List<PeliculaSerieDTO> dtos = this.peliculaSerieMapper.peliculaSerieEntitySet2DTOList(entities, true);
        return dtos;
    }
    
    //Agregar Pesonaje
    public void addPersonaje(Long id, Long idPersonaje){
        PeliculaSerieEntity entity = this.peliculaSerieRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = this.personajeService.getEntityById(idPersonaje);
        entity.addPersonaje(personajeEntity);
        this.peliculaSerieRepository.save(entity);
    }
    
    //Eliminar Pelicula
    public void removePersonaje(Long id, Long idPersonaje){
        PeliculaSerieEntity entity = this.peliculaSerieRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = this.personajeService.getEntityById(idPersonaje);
        entity.removePersonaje(personajeEntity);
        this.peliculaSerieRepository.save(entity);
    }

    
    
}
