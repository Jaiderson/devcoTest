package com.devco.testingreso.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devco.testingreso.entities.Candidato;
import com.devco.testingreso.services.ICandidatoService;
import com.devco.testingreso.utils.MensajeError;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/candidatos")
public class CandidatoController {

    private ICandidatoService candidatoService;

    @GetMapping
    @ApiOperation(value = "Permite buscar un candidato registrados dadu su DNI.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Candidato encontrado."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Candidato para el <b>dni<b> no existe.")
    })
    public ResponseEntity<Candidato> buscarCandidatoPorDni(@ApiParam(name="dni", value="Numero de documento asociado a un candidato.")
               @RequestParam(name="dni", required = true) String dni){
            Candidato candidato = candidatoService.buscarCandidatoPorDni(dni);
            if(null == candidato) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato con DNI = " + dni + " no encontrado.");
            }
        return ResponseEntity.ok(candidato); 
    }

    @GetMapping
    @ApiOperation(value = "Permite listar los candidatos registrados u opcionalmente buscar por su id.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Candidato(es) encontrado(s)."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Candidato para el <b>idCandidato<b> no existe.")
    })
    public ResponseEntity<List<Candidato>> listarTodosCandidatos(@ApiParam(name="idCandidato", value="Valor opcional para traer los roles de un candidato.")
               @RequestParam(name="idCandidato", required = false) Long idCandidato){
        List<Candidato> candidatos = new ArrayList<>();
        if(null == idCandidato) {
            candidatos = candidatoService.consultarCandidatos();
            if(candidatos.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No existen roles registrados.");
            }
        }
        else {
            Candidato rol = candidatoService.buscarCandidatoPorId(idCandidato);
            if(null == rol) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato con id "+idCandidato+" no encontrado.");
            }
            else {
                candidatos.add(rol);
            }
        }
        return ResponseEntity.ok(candidatos); 
    }

    @GetMapping
    @ApiOperation(value = "Permite listar roles registrados u opcionalmente buscar un rol por su id.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Candidato(es) encontrado(s)."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Candidato para el <b>idCandidato<b> no existe.")
    })
    public ResponseEntity<List<Candidato>> listarCandidatosPorNombre(@ApiParam(name="nombre", required = true, value="Valor con el nombre de los candidatos a buscar.")
               @RequestParam(name="nombre", required = true) String nombre){
        List<Candidato> candidatos = candidatoService.consultarCandidatosPorNombre(nombre);
        if(candidatos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatos con nombre '"+nombre+"' no encontrados.");
        }
        return ResponseEntity.ok(candidatos); 
    }

    @PostMapping
    @ApiOperation(value = "Permite crear un candidato nuevo, si el candidato ya existe regresa el candidato existente.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Candidato ya existe."),
        @ApiResponse(code = 201, message = "Candidato creado."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion del candidato incompleta.")
    })
    public ResponseEntity<Candidato> crearCandidato(@ApiParam(name="candidato", value="Candidato a crear", required = true)
            @Valid @RequestBody Candidato candidato, BindingResult result){
        if(result.hasErrors()) {
            MensajeError msnError = new MensajeError("NEW-RECORD");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
        }
        Candidato nuevoCandidato = candidatoService.crearCandidato(candidato);
        HttpStatus status = nuevoCandidato.isExistente() ? HttpStatus.OK : HttpStatus.CREATED; 

        return ResponseEntity.status(status).body(nuevoCandidato);
    }

    @PutMapping(value = "/{idCandidato}")
    @ApiOperation(value = "Permite modificar un la informacion de un candidato existente.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Candidato actualizado correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion del rol incompleta."),
        @ApiResponse(code = 204, message = "Candidato actualizado correctamente.")
    })
    public ResponseEntity<Candidato> modificarCandidato(@ApiParam(name="idCandidato", value="Id obligatorio del candidato.", required = true) 
            @PathVariable("idCandidato") Long idCandidato, 
            @ApiParam(name="Candidato", value="Candidato a actualizar.") 
            @Valid @RequestBody Candidato candidato, BindingResult result){
        if(result.hasErrors()) {
            MensajeError msnError = new MensajeError("UPDATE-RECORD");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
        }
        Candidato actCandidato = candidatoService.modificarCandidato(candidato);
        HttpStatus status = null == actCandidato ? HttpStatus.NOT_FOUND : HttpStatus.OK; 

        return ResponseEntity.status(status).body(candidato);
    }

    @DeleteMapping(value="/{idCandidato}")
    @ApiOperation(value = "Permite eliminar un rol existente.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Candidato eliminado correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Candidato no encontrado.")
    })
    public ResponseEntity<Candidato> eliminarCandidato(@ApiParam(name="idCandidato", required = true, value="Id del candidato a eliminar.") 
            @PathVariable("idCandidato") Long idCandidato){

        Candidato candidato = candidatoService.eliminarCandidato(idCandidato);
        if(null == candidato) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato con id = "+idCandidato+" no encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(candidato);
    }

    @DeleteMapping(value="/{dni}")
    @ApiOperation(value = "Permite eliminar un rol existente.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Candidato eliminado correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Candidato no encontrado.")
    })
    public ResponseEntity<Candidato> eliminarCandidato(@ApiParam(name="dni", required = true, value="Dni del candidato a eliminar.") 
            @PathVariable("dni") String dni){

        Candidato candidato = candidatoService.eliminarCandidato(dni);
        if(null == candidato) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato con DNI = "+dni+" no encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(candidato);
    }
}
