package com.alkemy.icons.icons.repository.specifications;

import com.alkemy.icons.icons.dto.PersonajeFiltersDTO;
import com.alkemy.icons.icons.entity.PeliculaSerieEntity;
import com.alkemy.icons.icons.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class PersonajeSpecification {
    
    public Specification<PersonajeEntity> getByFilters(PersonajeFiltersDTO filtersDTO){
        return(root, query, criteriaBuilder) -> {
                        
            List<Predicate> predicates = new ArrayList<>();
            
            //Name field
            if(StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getName().toLowerCase() + "%")); //Consultar pattern:
            } 
            
            //Age field             
            if(StringUtils.hasLength(filtersDTO.getAge())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("edad")),
                                "%" + filtersDTO.getAge() + "%")); //Consultar pattern:
            } 
          
            //Movies
            if(!CollectionUtils.isEmpty(filtersDTO.getMovies())){
                Join<PeliculaSerieEntity, PersonajeEntity> join = root.join("peliculasSeries", JoinType.INNER); //Consultar attributeName:
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
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