package com.springboot.examen.service;

import com.springboot.examen.entity.AsignaturaEntity;
import com.springboot.examen.exception.DBException;
import com.springboot.examen.repository.Asignaturarepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{


    @Autowired
    private Asignaturarepository asignaturarepository;

    @Override
    public Iterable<AsignaturaEntity> findAllAsignaturas() {
        try {
            return asignaturarepository.findAll();
        } catch (
                DataAccessException e) {
            throw new DBException(e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }
}
