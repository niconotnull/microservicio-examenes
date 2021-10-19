package com.springboot.examen.service;

import com.springboot.examen.entity.AsignaturaEntity;

public interface AsignaturaService {

    Iterable<AsignaturaEntity> findAllAsignaturas();

}
