package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.GeneroDTO;
import com.alkemy.icons.icons.entity.GeneroEntity;
import com.alkemy.icons.icons.mapper.GeneroMapper;
import com.alkemy.icons.icons.repository.GeneroRepository;
import com.alkemy.icons.icons.service.GeneroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService{
    
    @Autowired
    private GeneroMapper generoMapper;
    
    @Autowired
    private GeneroRepository generoRepository;
    
    @Override
    public GeneroDTO save(GeneroDTO dto){
        //Conversion a entity
        GeneroEntity entity = generoMapper.generoDTO2Entity(dto);
        //Guardado
        GeneroEntity entitySaved = generoRepository.save(entity);
        //Conversion a DTO
        GeneroDTO result = generoMapper.generoEntity2DTO(entitySaved);
        System.out.println("GUARDAR GENERO");
        return result;
                
                
                
              
                
    }

    @Override
    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> generoEntities = generoRepository.findAll();   
        List<GeneroDTO> result = generoMapper.generoEntityList2DTOList(generoEntities);
        return result;
 }
    
}
