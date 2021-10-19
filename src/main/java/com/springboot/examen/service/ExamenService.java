package com.springboot.examen.service;

import com.springboot.examen.entity.ExamenEntity;
import com.springboot.examen.generic.GenericService;
import com.springboot.examen.repository.Asignaturarepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExamenService extends GenericService<ExamenEntity> {

    List<ExamenEntity> findByNombre(String termino);

    Iterable<Integer> findExamenesIdsConRespuestasByPreguntasIds(List<Integer> preguntasIds);

    List<ExamenEntity> findAll();

    Page<ExamenEntity> findAll(Pageable pageable);

}
