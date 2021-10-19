package com.springboot.examen.service;

import com.springboot.examen.entity.ExamenEntity;
import com.springboot.examen.exception.DBException;
import com.springboot.examen.generic.GenericServiceImpl;
import com.springboot.examen.repository.Asignaturarepository;
import com.springboot.examen.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends GenericServiceImpl<ExamenEntity, ExamenRepository> implements ExamenService{

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public List<ExamenEntity> findAll() {
        try {
            return (List<ExamenEntity>) repository.findAll();
        } catch (
                DataAccessException e) {
            throw new DBException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ExamenEntity> findAll(Pageable pageable) {
        return examenRepository.findAllByOrderByIdDesc(pageable);
    }



    @Override
    @Transactional(readOnly = true)
    public List<ExamenEntity> findByNombre(String termino) {
        try {
            return  repository.findByNombre(termino);
        } catch (
                DataAccessException e) {
            throw new DBException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Integer> findExamenesIdsConRespuestasByPreguntasIds(List<Integer> preguntasIds) {
        return repository.findExamenesIdsConRespuestasByPreguntasIds(preguntasIds);
    }

}
