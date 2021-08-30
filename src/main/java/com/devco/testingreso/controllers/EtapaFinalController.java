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

import com.devco.testingreso.entities.EtapaFinal;
import com.devco.testingreso.entities.EtapaInicial;
import com.devco.testingreso.services.IEtapaFinalService;
import com.devco.testingreso.utils.MensajeError;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping(value = "/etapasFinales")
public class EtapaFinalController {

	private static final String NO_EXISTEN_ETAPAS_FINALES = "No existen etapas intermedias registradas.";

    @Autowired
    private IEtapaFinalService etapaFinalService;	

    @GetMapping(value = "/id2/{idEtapaIntermedia}")
    @ApiOperation(value = "Permite buscar una etapa final registrada por el id de la etapa intermedia asociada.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa final encontrada."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Etapa final no encontrada.")
    })
    public ResponseEntity<EtapaFinal> buscarEtapaFinalPorIdEtapaInicial(
    		@ApiParam(name="idEtapaIntermedia", value="Identificador unico de la etapa intermedia.")
    	    @PathVariable(name="idEtapaIntermedia", required = true) Long idEtapaIntermedia){
        EtapaFinal etapaFinal = etapaFinalService.buscarEtapaFinalPorIdEtapaIntermedia(idEtapaIntermedia);
        if(null == etapaFinal) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, NO_EXISTEN_ETAPAS_FINALES);
        }
        return ResponseEntity.ok(etapaFinal); 
    }

    @GetMapping(value = "/id/{idEtapaFinal}")
    @ApiOperation(value = "Permite buscar una etapa final registrada por su id.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa final encontrada."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Etapa intermedia no encontrada.")
    })
    public ResponseEntity<EtapaFinal> buscarEtapaFinalPorId(
    		@ApiParam(name="idEtapaFinal", value="Identificador unico de la etapa intermedia.")
    	    @PathVariable(name="idEtapaFinal", required = true) Long idEtapaFinal){
        EtapaFinal etapasIntermedia = etapaFinalService.buscarEtapaFinalPorId(idEtapaFinal);
        if(null == etapasIntermedia) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, NO_EXISTEN_ETAPAS_FINALES);
        }
        return ResponseEntity.ok(etapasIntermedia); 
    }

    @GetMapping(value = "/pro/{promedio}")
    @ApiOperation(value = "Permite listar todas la etapas finales registradas donde  "
    		            + "el promedio sea mayor a <b>promedio</b>")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas finales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Valor promedio errado. debe ser un % (Valor de 0 a 1)"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaFinal>> listarTodasEtapasInicialesPorCalificaciones(
    		@ApiParam(name="promedio", value="Valor promedio de la etapa final.")
    	    @PathVariable(name="promedio", required = true) Float promedio){

    	if(!EtapaInicial.isOkCalificacion(promedio)) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor promedio de la etapa final errado.");
    	}

    	List<EtapaFinal> etapasFinales = etapaFinalService.consultarEtapaFinalPorPromedio(promedio);
        if(etapasFinales.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_FINALES);
        }
        return ResponseEntity.ok(etapasFinales); 
    }

    @GetMapping
    @ApiOperation(value = "Permite listar todas la etapas finales registradas.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas finales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaFinal>> listarTodasEtapasIniciales(){
        List<EtapaFinal> etapasIntermedias = etapaFinalService.consultarEtapaFinales();
        if(etapasIntermedias.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_FINALES);
        }
        return ResponseEntity.ok(etapasIntermedias); 
    }

    @PostMapping
    @ApiOperation(value = "Permite crear el registro de una etapa final nueva.")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Etapa final creada."),
        @ApiResponse(code = 304, message = "Etapa final ya existe pata la etapa intermedia relacionada."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion del candidato incompleta.")
    })
    public ResponseEntity<EtapaFinal> crearEtapaFinal(
    		@ApiParam(name="etapaFinal", value="Etapa final a crear.", required = true)
            @Valid @RequestBody EtapaFinal etapaFinal, BindingResult result){
        if(result.hasErrors()) {
            MensajeError msnError = new MensajeError("NEW-RECORD");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
        }
        EtapaFinal etapaIntermediaDb = etapaFinalService.crearEtapaFinal(etapaFinal);
        if(null == etapaIntermediaDb) {
        	throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Etapa final ya existe pata la etapa intermedia relacionada.");
        }
        return ResponseEntity.ok(etapaIntermediaDb);
    }

    @PutMapping(value = "/{idEtapaFinal}")
    @ApiOperation(value = "Permite modificar la informacion de una etapa final registrada.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa final actualizada correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion de la etapa final incompleta."),
        @ApiResponse(code = 204, message = "EtapaFinal actualizado correctamente.")
    })
    public ResponseEntity<EtapaFinal> modificarEtapaFinal(
    		@ApiParam(name="idEtapaFinal", value="Id obligatorio de la etapa final.",required = true) 
    		@PathVariable("idEtapaFinal") Long idEtapaFinal, 
            @ApiParam(name="etapaFinal", value="Etapa final a actualizar.")
            @Valid @RequestBody EtapaFinal etapaFinal, BindingResult result){
        if(result.hasErrors()) {
            MensajeError msnError = new MensajeError("UPDATE-RECORD");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
        }
        etapaFinal.setIdEtapaF(idEtapaFinal);
        EtapaFinal actEtapaFinal = etapaFinalService.modificarEtapaFinal(etapaFinal);

        if(null == actEtapaFinal) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etapa final a modificar no encontrada.");
		}
		return ResponseEntity.ok(actEtapaFinal);
    }

    @DeleteMapping(value="/{idEtapaFinal}")
    @ApiOperation(value = "Permite eliminar una etapa final existente.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa final eliminada correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Etapa inicial no encontrada.")
    })
    public ResponseEntity<EtapaFinal> eliminarEtapaFinal(
    		@ApiParam(name="idEtapaFinal", required = true, value="Id de la etapa final a eliminar.") 
            @PathVariable("idEtapaFinal") Long idEtapaFinal){

        EtapaFinal candidato = etapaFinalService.eliminarEtapaFinal(idEtapaFinal);
        if(null == candidato) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etapa final con id = "+idEtapaFinal+" no encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(candidato);
    }
}
