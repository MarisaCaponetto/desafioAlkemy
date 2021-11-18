package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.GeneroDTO;
import com.alkemy.icons.icons.entity.GeneroEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {
    //Conversion de DTO a Entidad
    public GeneroEntity generoDTO2Entity(GeneroDTO dto){
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setNombre(dto.getNombre());
        generoEntity.setImagen(dto.getImagen());
        
        return generoEntity;        
    }
    
    //Conversion de Entidad a DTO
    public GeneroDTO generoEntity2DTO(GeneroEntity entity){
        
        GeneroDTO dto = new GeneroDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        
        return dto;
    }
    
    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> generoEntities){
        List<GeneroDTO> generoDtos= new ArrayList<>();
        for(GeneroEntity aux : generoEntities){
            generoDtos.add(generoEntity2DTO(aux));
        }
        return generoDtos;
    }
}
