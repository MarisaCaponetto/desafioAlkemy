package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.dto.PeliculaSerieFiltersDTO;
import com.alkemy.icons.icons.entity.GeneroEntity;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import com.alkemy.icons.icons.mapper.PeliculaSerieMapper;
import com.alkemy.icons.icons.repository.PeliculaSerieRepository;
import com.alkemy.icons.icons.repository.specifications.PeliculaSerieSpecification;
import com.alkemy.icons.icons.service.PeliculaSerieService;
import com.alkemy.icons.icons.service.PersonajeService;
import java.util.List;
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
        
        
    
    
    
    
    public PeliculaSerieDTO save(PeliculaSerieDTO dto){
        //Conversion a entity
        PeliculaSerieEntity entity = peliculaSerieMapper.peliculaSerieDTO2Entity(dto);
        //Guardado
        PeliculaSerieEntity entitySaved = peliculaSerieRepository.save(entity);
        //Conversion a DTO
        PeliculaSerieDTO result = peliculaSerieMapper.peliculaSerieEntity2DTO(entitySaved, true);
        //PeliculaSerieDTO result = peliculaSerieMapper.peliculaSerieEntity2DTO(entitySaved);
        System.out.println("GUARDAR PELICULA O SERIE");
        return result;
    }

    @Override
    public List<PeliculaSerieDTO> getAllPeliculasSeries() {
        List<PeliculaSerieEntity> peliculaSerieEntities = peliculaSerieRepository.findAll();
        List<PeliculaSerieDTO> result = peliculaSerieMapper.peliculaSerieEntitySetList2DTOList(peliculaSerieEntities, true);
        
        //List<PeliculaSerieDTO> result = peliculaSerieMapper.peliculaSerieEntityList2DTOList(peliculaSerieEntities);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.peliculaSerieRepository.deleteById(id);
    }

    @Override
    public List<PeliculaSerieDTO> getByFilters(String name, GeneroEntity genre, String order) {
        PeliculaSerieFiltersDTO filtersDTO = new PeliculaSerieFiltersDTO(name, genre, order);
        List<PeliculaSerieEntity> entities = this.peliculaSerieRepository.findAll(this.peliculaSerieSpecification.getByFilters(filtersDTO));
        List<PeliculaSerieDTO> dtos = this.peliculaSerieMapper.peliculaSerieEntitySetList2DTOList(entities, true);
        return dtos;
        
    }
    
}
