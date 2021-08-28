package com.devco.testingreso.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devco.testingreso.entities.EtapaInicial;
import com.devco.testingreso.services.IEtapaInicialService;
import com.devco.testingreso.utils.MensajeError;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/etapasIniciales")
public class EtapaInicialController {

    @Autowired
    private IEtapaInicialService etapaInicialService;

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
        @ApiResponse(code = 400, message = "Informacion del rol incompleta."),
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
        EtapaInicial actEtapaInicial = etapaInicialService.modificarEtapaInicial(etapaInicial);
        HttpStatus status = null == actEtapaInicial ? HttpStatus.NOT_FOUND : HttpStatus.OK; 

        return ResponseEntity.status(status).body(etapaInicial);
    }


}
