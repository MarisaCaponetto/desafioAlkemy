package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.PeliculaSerieDTO;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import com.alkemy.icons.icons.mapper.PeliculaSerieMapper;
import com.alkemy.icons.icons.repository.PeliculaSerieRepository;
import com.alkemy.icons.icons.service.PeliculaSerieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaSerieServiceImpl implements PeliculaSerieService {
    
    @Autowired
    private PeliculaSerieMapper peliculaSerieMapper;
    
    @Autowired
    private PeliculaSerieRepository peliculaSerieRepository;
    
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
        List<PeliculaSerieDTO> result = peliculaSerieMapper.peliculaSerieEntityList2DTOList(peliculaSerieEntities, true);
        //List<PeliculaSerieDTO> result = peliculaSerieMapper.peliculaSerieEntityList2DTOList(peliculaSerieEntities);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.peliculaSerieRepository.deleteById(id);
    }
    
}
