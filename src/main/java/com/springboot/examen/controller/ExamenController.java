package com.springboot.examen.controller;

import com.springboot.examen.entity.ExamenEntity;
import com.springboot.examen.entity.PreguntaEntity;
import com.springboot.examen.exception.DBException;
import com.springboot.examen.generic.GenericController;
import com.springboot.examen.service.AsignaturaService;
import com.springboot.examen.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ExamenController  extends GenericController<ExamenEntity, ExamenService> {

    @Autowired
    private AsignaturaService asignaturaService;


    @GetMapping(value = "/listar")
    public ResponseEntity<?> findAll(){
        try {
            return new ResponseEntity<List<ExamenEntity>>(service.findAll(), HttpStatus.OK);
        }catch (DBException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/paginacion")
    public ResponseEntity<?> findAll(Pageable pageable){
        try {
            return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
        }catch (DBException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/respondidos-por-preguntas/")
    public ResponseEntity<?> obtenerExamenesIdsPorPreguntasIdsRespondidas(@RequestParam List<Integer> preguntaIds){
        return ResponseEntity.ok().body(service.findExamenesIdsConRespuestasByPreguntasIds(preguntaIds));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody ExamenEntity examen, BindingResult result, @PathVariable Integer id) {
        try {
            if (result.hasErrors()) {
                return this.validar(result);
            }

            Optional<ExamenEntity> o = Optional.ofNullable(service.findById(id));

            if (!o.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el examen con el id: " + id);
            }
            ExamenEntity examenDB = o.get();
            examenDB.setNombre(examen.getNombre());

            System.out.println(examen.getAsignaturaPadre().toString());
            System.out.println(examen.getAsignaturaHija().toString());

            examenDB.setPreguntas(examen.getPreguntas());
            examenDB.setAsignaturaHija(examen.getAsignaturaHija());
            examenDB.setAsignaturaPadre(examen.getAsignaturaPadre());

            return new ResponseEntity<>(service.save(examenDB), HttpStatus.OK);
        } catch (DBException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/filtrar/{termino}")
    public ResponseEntity<?> filtrar(@PathVariable String termino) {
        try {
            return new ResponseEntity<>( service.findByNombre(termino), HttpStatus.OK);
        } catch (DBException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/asignaturas/")
    public ResponseEntity<?> listarAsignaturas() {
        try {
            return new ResponseEntity<>(asignaturaService.findAllAsignaturas(), HttpStatus.OK);
        } catch (DBException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
