package com.devco.testingreso.controllers;

import java.util.Date;
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
import com.devco.testingreso.services.IEtapaInicialService;
import com.devco.testingreso.utils.Dato;
import com.devco.testingreso.utils.MensajeError;
import com.devco.testingreso.utils.Util;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/etapasIniciales")
public class EtapaInicialController {

	private static final String NO_EXISTEN_ETAPAS_INICIALES = "No existen etapas inciales registradas.";

    @Autowired
    private IEtapaInicialService etapaInicialService;

    @GetMapping(value = "/rol/{idRol}/tec/{calTecnica}/fecPostulacionIni/{fecPostulacionIni}/fecPostulacionFin/{fecPostulacionFin}")
    @ApiOperation(value = "Permite listar las etapas iniciales registradas por un rol especifico y entre "
    		            + "fechas de postulacion cuya calificacion tecnica sea mayor al valor ingresado.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas iniciales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 400, message = "Id del rol vacio, calificacion tecnica errada o Formato de fechas errado. (yyyy-mm-dd)"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaInicial>> listarEtapasInicialesPorRolEntreFechasCalTecnica(
    		@ApiParam(name="idRol", value="Id del rol.")
    	    @PathVariable(name="idRol", required = true) Long idRol,
    		@ApiParam(name="calTecnica", value="Valor de la calififcacion tecnica.")
    	    @PathVariable(name="calTecnica", required = true) Float calTecnica,
    		@ApiParam(name="fecPostulacionIni", value="Fecha postulacion inicial.")
    	    @PathVariable(name="fecPostulacionIni", required = true) String fecPostulacionIni,
    		@ApiParam(name="fecPostulacionFin", value="Fecha postulacion final.")
    	    @PathVariable(name="fecPostulacionFin", required = true) String fecPostulacionFin){

    	Dato fecIni = Util.stringToDate(fecPostulacionIni);
    	Dato fecFin = Util.stringToDate(fecPostulacionFin);
    	if(null == idRol || !EtapaInicial.isOkCalificacion(calTecnica) || !fecIni.isOk() || !fecFin.isOk()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id del rol vacio, calificacion tecnica errada o Formato de fechas errado. (yyyy-mm-dd)");
    	}
    	Date fechaIni = (Date) fecIni.getValor();
    	Date fechaFin = (Date) fecFin.getValor();
    	
    	List<EtapaInicial> etapasIniciales = etapaInicialService.consultarEtapasEntreFechaPostulacionRolCalTecnica(idRol, calTecnica, fechaIni, fechaFin);
        if(etapasIniciales.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INICIALES);
        }
        return ResponseEntity.ok(etapasIniciales); 
    }

    @GetMapping(value = "/rol/{idRol}/teo/{calTeorica}/fecPostulacionIni/{fecPostulacionIni}/fecPostulacionFin/{fecPostulacionFin}")
    @ApiOperation(value = "Permite listar las etapas iniciales registradas por un rol especifico y entre "
    		            + "fechas de postulacion cuya calificacion teorica sea mayor al valor ingresado.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas iniciales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 400, message = "Id del rol vacio, calificacion teoria errada o Formato de fechas errado. (yyyy-mm-dd)"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaInicial>> listarEtapasInicialesPorRolEntreFechasCalTeorica(
    		@ApiParam(name="idRol", value="Id del rol.")
    	    @PathVariable(name="idRol", required = true) Long idRol,
    		@ApiParam(name="calTeorica", value="Valor de la calififcacion teorica.")
    	    @PathVariable(name="calTeorica", required = true) Float calTeorica,
    		@ApiParam(name="fecPostulacionIni", value="Fecha postulacion inicial.")
    	    @PathVariable(name="fecPostulacionIni", required = true) String fecPostulacionIni,
    		@ApiParam(name="fecPostulacionFin", value="Fecha postulacion final.")
    	    @PathVariable(name="fecPostulacionFin", required = true) String fecPostulacionFin){

    	Dato fecIni = Util.stringToDate(fecPostulacionIni);
    	Dato fecFin = Util.stringToDate(fecPostulacionFin);
    	if(null == idRol || !EtapaInicial.isOkCalificacion(calTeorica) || !fecIni.isOk() || !fecFin.isOk()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id del rol vacio, calificacion teorica errada o Formato de fechas errado. (yyyy-mm-dd)");
    	}
    	Date fechaIni = (Date) fecIni.getValor();
    	Date fechaFin = (Date) fecFin.getValor();
    	
    	List<EtapaInicial> etapasIniciales = etapaInicialService.consultarEtapasEntreFechaPostulacionRolCalTeorica(idRol, calTeorica, fechaIni, fechaFin);
        if(etapasIniciales.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INICIALES);
        }
        return ResponseEntity.ok(etapasIniciales); 
    }

    @GetMapping(value = "/rol/{idRol}/fecPostulacionIni/{fecPostulacionIni}/fecPostulacionFin/{fecPostulacionFin}")
    @ApiOperation(value = "Permite listar las etapas iniciales registradas por un rol especifico y entre fechas de postulacion. (yyyy-mm-dd)")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas iniciales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 400, message = "Formato de fecha errado. (yyyy-mm-dd)"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaInicial>> listarEtapasInicialesPorRolEntreFechas(
    		@ApiParam(name="idRol", value="Id del rol.")
    	    @PathVariable(name="idRol", required = true) Long idRol,
    		@ApiParam(name="fecPostulacionIni", value="Fecha postulacion inicial.")
    	    @PathVariable(name="fecPostulacionIni", required = true) String fecPostulacionIni,
    		@ApiParam(name="fecPostulacionFin", value="Fecha postulacion final.")
    	    @PathVariable(name="fecPostulacionFin", required = true) String fecPostulacionFin){

    	Dato fecIni = Util.stringToDate(fecPostulacionIni);
    	Dato fecFin = Util.stringToDate(fecPostulacionFin);
    	if(null == idRol || !fecIni.isOk() || !fecFin.isOk()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id del rol vacio o Formato de fechas errado. (yyyy-mm-dd)");
    	}
    	Date fechaIni = (Date) fecIni.getValor();
    	Date fechaFin = (Date) fecFin.getValor();
    	
    	List<EtapaInicial> etapasIniciales = etapaInicialService.consultarEtapaInicialesPorRolEntreFecPostulacion(idRol, fechaIni, fechaFin);
        if(etapasIniciales.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INICIALES);
        }
        return ResponseEntity.ok(etapasIniciales); 
    }

    @GetMapping(value = "/fecPostulacion/{fecPostulacion}")
    @ApiOperation(value = "Permite listar las etapas iniciales registradas data una fecha. (yyyy-mm-dd)")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas iniciales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 400, message = "Formato de fecha errado. (yyyy-mm-dd)"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaInicial>> listarEtapasInicialesPorFecha(@ApiParam(name="fecPostulacion", value="Fecha postulacion.")
    	   @PathVariable(name="fecPostulacion", required = true) String fecPostulacion){
    	Dato dato = Util.stringToDate(fecPostulacion);
    	if(!dato.isOk()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha errado. (yyyy-mm-dd)");
    	}
    	Date fecha = (Date) dato.getValor();
    	List<EtapaInicial> etapasIniciales = etapaInicialService.consultarEtapaInicialesPorFecPostulacion(fecha);
        if(etapasIniciales.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INICIALES);
        }
        return ResponseEntity.ok(etapasIniciales); 
    }

    @GetMapping(value = "/dni/{dni}")
    @ApiOperation(value = "Permite listar las etapas iniciales registradas de un candidato dado su dni.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas iniciales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 400, message = "DNI invalido"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaInicial>> listarEtapasInicialesPorDniCandidato(
    		@ApiParam(name="dni", value="Numero de documento de identidad de un candidato.")
    	    @PathVariable(name="dni", required = true) String dni){
    	if(null == dni) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DNI no puede ser vacia.");
    	}
    	List<EtapaInicial> etapasIniciales = etapaInicialService.consultarEtapaInicialsPorDni(dni);
        if(etapasIniciales.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INICIALES);
        }
        return ResponseEntity.ok(etapasIniciales); 
    }

    @GetMapping(value = "/fecPostulacionIni/{fecPostulacionIni}/fecPostulacionFin/{fecPostulacionFin}"  )
    @ApiOperation(value = "Permite listar las etapas iniciales registradas entre fechas de postulacion. (yyyy-mm-dd)")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas iniciales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 400, message = "Formato de fecha errado. (yyyy-mm-dd)"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaInicial>> listarEtapasInicialesPorFechas(
    		@ApiParam(name="fecPostulacionIni", value="Fecha postulacion inicial.")
    	    @PathVariable(name="fecPostulacionIni", required = true) String fecPostulacionIni,
    		@ApiParam(name="fecPostulacionFin", value="Fecha postulacion final.")
    	    @PathVariable(name="fecPostulacionFin", required = true) String fecPostulacionFin){

    	Dato fecIni = Util.stringToDate(fecPostulacionIni);
    	Dato fecFin = Util.stringToDate(fecPostulacionFin);
    	if(!fecIni.isOk() || !fecFin.isOk() ) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fechas errado. (yyyy-mm-dd)");
    	}
    	Date fechaIni = (Date) fecIni.getValor();
    	Date fechaFin = (Date) fecFin.getValor();
    	
    	List<EtapaInicial> etapasIniciales = etapaInicialService.consultarEtapaInicialesEntreFecPostulacion(fechaIni, fechaFin);
        if(etapasIniciales.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INICIALES);
        }
        return ResponseEntity.ok(etapasIniciales); 
    }

    @GetMapping
    @ApiOperation(value = "Permite listar todas la etapas iniciales registradas.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapas iniciales encontradas."),
        @ApiResponse(code = 204, message = "Sin contenido."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
    })
    public ResponseEntity<List<EtapaInicial>> listarTodasEtapasIniciales(){
        List<EtapaInicial> etapasIniciales = etapaInicialService.consultarEtapaIniciales();
        if(etapasIniciales.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NO_CONTENT, NO_EXISTEN_ETAPAS_INICIALES);
        }
        return ResponseEntity.ok(etapasIniciales); 
    }

    @PostMapping
    @ApiOperation(value = "Permite crear el registro de una etapainicial nueva.")
    @ApiResponses({
        @ApiResponse(code = 201, message = "EtapaInicial creado."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion del candidato incompleta.")
    })
    public ResponseEntity<EtapaInicial> crearEtapaInicial(@ApiParam(name="etapaInicial", value="Etapa inicial a crear", required = true)
            @Valid @RequestBody EtapaInicial etapaInicial, BindingResult result){
        if(result.hasErrors()) {
            MensajeError msnError = new MensajeError("NEW-RECORD");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
        }
        return ResponseEntity.ok(etapaInicialService.crearEtapaInicial(etapaInicial));
    }

    @PutMapping(value = "/{idEtapaInicial}")
    @ApiOperation(value = "Permite modificar un la informacion de una etapa inicial registrada.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "EtapaInicial actualizado correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 400, message = "Informacion del etapa inicial incompleta."),
        @ApiResponse(code = 204, message = "EtapaInicial actualizado correctamente.")
    })
    public ResponseEntity<EtapaInicial> modificarEtapaInicial(@ApiParam(name="idEtapaInicial", value="Id obligatorio de la etapa inicial.", 
            required = true) @PathVariable("idEtapaInicial") Long idEtapaInicial, 
            @ApiParam(name="etapaInicial", value="Etapa inicial a actualizar.")
            @Valid @RequestBody EtapaInicial etapaInicial, BindingResult result){
        if(result.hasErrors()) {
            MensajeError msnError = new MensajeError("UPDATE-RECORD");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
        }
        etapaInicial.setIdEtapa(idEtapaInicial);
        EtapaInicial actEtapaInicial = etapaInicialService.modificarEtapaInicial(etapaInicial);
        if(null == actEtapaInicial) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etapa inicial a modificar no encontrada.");
		}
		return ResponseEntity.ok(actEtapaInicial); 
    }

    @DeleteMapping(value="/{idEtapaInicial}")
    @ApiOperation(value = "Permite eliminar una etapa inicial existente.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Etapa inicial eliminada correctamente."),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 404, message = "Etapa inicial no encontrada.")
    })
    public ResponseEntity<EtapaInicial> eliminarEtapaInicial(@ApiParam(name="idEtapaInicial", required = true, value="Id de la etapa inicial a eliminar.") 
            @PathVariable("idEtapaInicial") Long idEtapaInicial){

        EtapaInicial candidato = etapaInicialService.eliminarEtapaInicial(idEtapaInicial);
        if(null == candidato) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EtapaInicial con id = "+idEtapaInicial+" no encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(candidato);
    }

}
