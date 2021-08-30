package com.devco.testingreso.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devco.testingreso.entities.EtapaInicial;
import com.devco.testingreso.entities.EtapaIntermedia;
import com.devco.testingreso.services.IEtapaIntermediaService;
import com.devco.testingreso.utils.MensajeError;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/etapasIntermedias")
public class EtapaIntermediaController {

	private static final String NO_EXISTEN_ETAPAS_INTERMEDIAS = "No existen etapas intermedias registradas.";

    @Autowired
    private IEtapaIntermediaService etapaIntermediaService;

    @GetMapping(value = "/id2/{idEtapaInicial}")
    @ApiOperation(value = "Permite buscar una etapa intermedia registrada por el id de la etapa inicial asociada.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa intermedia encontrada."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Etapa intermedia no encontrada.")
    })
    public ResponseEntity<EtapaIntermedia> buscarEtapaIntermediaPorIdEtapaInicial(
    		@ApiParam(name="idEtapaInicial", value="Identificador unico de la etapa intermedia.")
    	    @PathVariable(name="idEtapaInicial", required = true) Long idEtapaInicial){
        EtapaIntermedia etapaIntermedia = etapaIntermediaService.buscarEtapaIntermediaPorIdEtapaInicial(idEtapaInicial);
        if(null == etapaIntermedia) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, NO_EXISTEN_ETAPAS_INTERMEDIAS);
        }
        return ResponseEntity.ok(etapaIntermedia); 
    }

    @GetMapping(value = "/id/{idEtapaIntermedia}")
    @ApiOperation(value = "Permite buscar una etapas intermedia registrada por su id.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa intermedia encontrada."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Etapa intermedia no encontrada.")
    })
    public ResponseEntity<EtapaIntermedia> buscarEtapaIntermediaPorId(
    		@ApiParam(name="idEtapaIntermedia", value="Identificador unico de la etapa intermedia.")
    	    @PathVariable(name="idEtapaIntermedia", required = true) Long idEtapaIntermedia){
        EtapaIntermedia etapasIntermedia = etapaIntermediaService.buscarEtapaIntermediaPorId(idEtapaIntermedia);
        if(null == etapasIntermedia) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, NO_EXISTEN_ETAPAS_INTERMEDIAS);
        }
        return ResponseEntity.ok(etapasIntermedia); 
    }

    @GetMapping(value = "/psi/{calPsicologica}/med/{calMedica}")
    @ApiOperation(value = "Permite listar todas la etapas intermedias registradas donde las calificaciones psicologicas y "
    		            + "tecnicas sean mayores a las definidas. <b>(calPsicologica - calMedica)</b>")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas intermedias encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Valor de las calificaciones erradas. debe ser un % (Valor de 0 a 1)"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaIntermedia>> listarTodasEtapasInicialesPorCalificaciones(
    		@ApiParam(name="calPsicologica", value="Calificacion psicologica del rol.")
    	    @PathVariable(name="calPsicologica", required = true) Float calPsicologica,
    		@ApiParam(name="calMedica", value="Calificacion medica del rol.")
    	    @PathVariable(name="calMedica", required = true) Float calMedica){

    	if(!EtapaInicial.isOkCalificacion(calPsicologica) || !EtapaInicial.isOkCalificacion(calMedica)) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor de las calificaciones erradas.");
    	}

    	List<EtapaIntermedia> etapasIntermedias = etapaIntermediaService.consultarEtapaIntermediaPorCalificaciones(calPsicologica, calMedica);
        if(etapasIntermedias.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INTERMEDIAS);
        }
        return ResponseEntity.ok(etapasIntermedias); 
    }

    @GetMapping
    @ApiOperation(value = "Permite listar todas la etapas intermedias registradas.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas intermedias encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaIntermedia>> listarTodasEtapasIniciales(){
        List<EtapaIntermedia> etapasIntermedias = etapaIntermediaService.consultarEtapaIntermedias();
        if(etapasIntermedias.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INTERMEDIAS);
        }
        return ResponseEntity.ok(etapasIntermedias); 
    }

    @PostMapping
    @ApiOperation(value = "Permite crear el registro de una etapa intermedia nueva.")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Etapa intermedia creada."),
        @ApiResponse(code = 304, message = "Etapa intermedia ya existe pata la etapa inicial relacionada."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion del candidato incompleta.")
    })
    public ResponseEntity<EtapaIntermedia> crearEtapaIntermedia(@ApiParam(name="etapaIntermedia", value="Etapa intermedia a crear.", required = true)
            @Valid @RequestBody EtapaIntermedia etapaIntermedia, BindingResult result){
        if(result.hasErrors()) {
            MensajeError msnError = new MensajeError("NEW-RECORD");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
        }
        EtapaIntermedia etapaIntermediaDb = etapaIntermediaService.crearEtapaIntermedia(etapaIntermedia);
        if(null == etapaIntermediaDb) {
        	throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Etapa intermedia ya existe pata la etapa inicial relacionada.");
        }
        return ResponseEntity.ok(etapaIntermediaDb);
    }

    @PutMapping(value = "/{idEtapaIntermedia}")
    @ApiOperation(value = "Permite modificar un la informacion de una etapa intermedia registrada.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa intermedia actualizado correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion de la etapa intermedia incompleta."),
        @ApiResponse(code = 204, message = "EtapaIntermedia actualizado correctamente.")
    })
    public ResponseEntity<EtapaIntermedia> modificarEtapaIntermedia(@ApiParam(name="idEtapaIntermedia", value="Id obligatorio de la etapa intermedia.", 
            required = true) @PathVariable("idEtapaIntermedia") Long idEtapaIntermedia, 
            @ApiParam(name="etapaIntermedia", value="Etapa intermedia a actualizar.")
            @Valid @RequestBody EtapaIntermedia etapaIntermedia, BindingResult result){
        if(result.hasErrors()) {
            MensajeError msnError = new MensajeError("UPDATE-RECORD");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
        }
        etapaIntermedia.setIdEtapaM(idEtapaIntermedia);
        EtapaIntermedia actEtapaIntermedia = etapaIntermediaService.modificarEtapaIntermedia(etapaIntermedia);

        if(null == actEtapaIntermedia) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etapa intermedia a modificar no encontrada.");
		}
		return ResponseEntity.ok(actEtapaIntermedia); 
    }

    @DeleteMapping(value="/{idEtapaIntermedia}")
    @ApiOperation(value = "Permite eliminar una etapa intermedia existente.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa intermedia eliminada correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Etapa inicial no encontrada.")
    })
    public ResponseEntity<EtapaIntermedia> eliminarEtapaIntermedia(@ApiParam(name="idEtapaIntermedia", required = true, value="Id de la etapa intermedia a eliminar.") 
            @PathVariable("idEtapaIntermedia") Long idEtapaIntermedia){

        EtapaIntermedia candidato = etapaIntermediaService.eliminarEtapaIntermedia(idEtapaIntermedia);
        if(null == candidato) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etapa intermedia con id = "+idEtapaIntermedia+" no encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(candidato);
    }

}
