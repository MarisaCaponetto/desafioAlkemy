package com.alkemy.icons.icons.repository.specifications;

import com.alkemy.icons.icons.dto.PeliculaSerieFiltersDTO;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class PeliculaSerieSpecification {
    
    public Specification<PeliculaSerieEntity> getByFilters(PeliculaSerieFiltersDTO filtersDTO){
        return(root, query, criteriaBuilder) -> {
            
            List<Predicate> predicates = new ArrayList<>();
            
            //Name field
            if(StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"));
            }
            
            //Genre field
            if(!Objects.isNull(filtersDTO.getGenero().getId())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("idGenero")),
                                "%" + filtersDTO.getGenero().getId() + "%"));
               
            }
             //Remove duplicates
            query.distinct(true);
            
            //Order resolver
            String orderByField = "nombre";
            query.orderBy(
                filtersDTO.isASC() ?
                        criteriaBuilder.asc(root.get(orderByField)) :
                        criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
