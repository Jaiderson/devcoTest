package com.devco.testingreso.controllers;

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
import org.springframework.web.server.ResponseStatusException;

import com.devco.testingreso.entities.Rol;
import com.devco.testingreso.services.IRolService;
import com.devco.testingreso.utils.MensajeError;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "/roles")
public class RolController {

	@Autowired
	private IRolService rolService;

	@GetMapping
	@ApiOperation(value = "Permite listar todos los roles registrados.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Lista de roles encontrados."),
		@ApiResponse(code = 204, message = "Sin contenido."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
	})
	public ResponseEntity<List<Rol>> listarRoles(){
		List<Rol> roles = rolService.consultarRoles();
		if(roles.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No existen roles registrados.");
		}
		return ResponseEntity.ok(roles); 
	}

	@GetMapping(value = "/{idRol}")
	@ApiOperation(value = "Permite listar rol dado su id.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Rol encontrado."),
		@ApiResponse(code = 204, message = "Sin Contenido."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
		@ApiResponse(code = 404, message = "Rol para el <b>idRol<b> no existe."),
		@ApiResponse(code = 405, message = "Por favor especifique el id.")
	})
	public ResponseEntity<Rol> listarRol(@ApiParam(name="idRol", value="Valor opcional para traer los roles de un candidato.")
		   @PathVariable(name="idRol", required = true) Long idRol){

		Rol rol = rolService.buscarRol(idRol);
		if(null == rol) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol con id "+idRol+" no encontrado.");
		}
		return ResponseEntity.ok(rol);
	}

	@PostMapping
	@ApiOperation(value = "Permite crear un rol nuevo, si el rol ya existe retorna el existente.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Rol ya existe."),
		@ApiResponse(code = 201, message = "Rol creado correctamente."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
		@ApiResponse(code = 400, message = "Informacion del rol incompleta.")
	})
	public ResponseEntity<Rol> crearRol(@ApiParam(name="rol", value="Rol a crear", required = true)
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
		@ApiResponse(code = 200, message = "Rol modificado."),
		@ApiResponse(code = 400, message = "Informacion del rol incompleta."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
		@ApiResponse(code = 404, message = "Rol no encontrado."),
		@ApiResponse(code = 405, message = "Por favor especifique el id.")
	})
	public ResponseEntity<Rol> modificarRol(@ApiParam(name="idRol", value="Id obligatorio del rol.", required = true) 
	        @PathVariable("idRol") Long idRol, 
			@ApiParam(name="Rol", value="Rol a actualizar.") 
			@Valid @RequestBody Rol rol, BindingResult result){
		if(result.hasErrors()) {
			MensajeError msnError = new MensajeError("UPDATE-RECORD");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msnError.getMensaje(result));
		}
		rol.setIdRol(idRol);
		Rol nuevoRol = rolService.modificarRol(rol);
        if(null == nuevoRol) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol a modificar no encontrada.");
		}
		return ResponseEntity.ok(nuevoRol); 
	}

	@DeleteMapping(value="/{idRol}")
	@ApiOperation(value = "Permite eliminar un rol existente.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Rol eliminado correctamente."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
		@ApiResponse(code = 404, message = "Rol no encontrado."),
		@ApiResponse(code = 405, message = "Por favor especifique el id.")
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
