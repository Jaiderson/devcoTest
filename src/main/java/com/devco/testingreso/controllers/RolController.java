package com.devco.testingreso.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.devco.testingreso.services.IRolService;
import com.devco.testingreso.entities.Rol;
import com.devco.testingreso.utils.MensajeError;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Controller
@RequestMapping(value = "/roles")
public class RolController {

	@Autowired
	private IRolService rolService;

	@GetMapping
	@ApiOperation(value = "Permite listar roles registrados u opcionalmente buscar un rol por su id.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Rol(es) encontrado(s)."),
		@ApiResponse(code = 204, message = "Sin Contenido."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
		@ApiResponse(code = 404, message = "Rol para el <b>idRol<b> no existe.")
	})
	public ResponseEntity<List<Rol>> listarRoles(@ApiParam(name="idRol", value="Valor opcional para traer los roles de un candidato.")
			   @RequestParam(name="idRol", required = false) Long idRol){
		List<Rol> roles = new ArrayList<Rol>();
		if(null == idRol) {
			roles = rolService.consultarRoles();
			if(roles.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No existen roles registrados.");
			}
		}
		else {
			Rol rol = rolService.buscarRol(idRol);
			if(null == rol) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol con id "+idRol+" no encontrado.");
			}
			else {
				roles.add(rol);
			}
		}
		return ResponseEntity.ok(roles); 
	}	

	@PostMapping
	@ApiOperation(value = "Permite crear un rol nuevo, si el rol ya existe lo ctualiza.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Rol ya existe."),
		@ApiResponse(code = 201, message = "Rol Creado."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
		@ApiResponse(code = 400, message = "Informacion del rol incompleta.")
	})
	public ResponseEntity<Rol> crearRol(@ApiParam(name="rol", value="Rol a crear")
			@Valid @RequestBody Rol rol, BindingResult result){
		if(result.hasErrors()) {
			MensajeError msnError = new MensajeError("NEW-RECORD");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
		}
		Rol nuevoRol = rolService.crearRol(rol);
		HttpStatus status = nuevoRol.isExistente() ? HttpStatus.OK : HttpStatus.CREATED; 

		return ResponseEntity.status(status).body(nuevoRol);
	}

	@PutMapping(value = "/{idRol}")
	@ApiOperation(value = "Permite modificar un rol existente.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Rol ya existe."),
		@ApiResponse(code = 201, message = "Rol Creado."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
		@ApiResponse(code = 400, message = "Informacion del rol incompleta.")
	})
	public ResponseEntity<Rol> modificarRol(@ApiParam(name="idRol", value="Id obligatorio del rol.") 
	        @PathVariable("idRol") Long idRol, 
			@ApiParam(name="Rol", value="Rol a actualizar.") 
			@Valid @RequestBody Rol rol, BindingResult result){
		return crearRol(rol, result);
	}

	@DeleteMapping(value="/{idRol}")
	@ApiOperation(value = "Permite eliminar un rol existente.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Rol eliminado correctamente."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
		@ApiResponse(code = 404, message = "Rol no encontrado.")
	})
	public ResponseEntity<Rol> eliminarProduto(@ApiParam(name="idRol", required = true, value="Id del rol a eliminar.") 
			@PathVariable("idRol") Long idRol){

		Rol rol = rolService.eliminarRol(idRol);
		if(null == rol) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol con id = "+idRol+" no encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(rol);
	}
}
