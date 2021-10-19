package com.springboot.examen.repository;

import com.springboot.examen.entity.ExamenEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExamenRepository extends PagingAndSortingRepository<ExamenEntity, Integer> {

    @Query("select e from ExamenEntity e where e.nombre like %?1%")
    List<ExamenEntity> findByNombre(String termino);

    @Query("select e.id from PreguntaEntity p join p.examen e where p.id in ?1 group by e.id")
    Iterable<Integer> findExamenesIdsConRespuestasByPreguntasIds(List<Integer> preguntasIds);

    Page<ExamenEntity> findAllByOrderByIdDesc(Pageable pageable);


}
