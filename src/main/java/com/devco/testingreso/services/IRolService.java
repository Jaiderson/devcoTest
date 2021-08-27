package com.devco.testingreso.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.Rol;

@Service
public interface IRolService {

	/***
	 * Busca un rol dado su identificador unico <b>idRol</b>.
	 * 
	 * @param idRol Identificador unico del rol. 
	 * @return Rol asociado al <b>idRol</b> o null si no se encuentra.
	 */
	public Rol buscarRol(Long idRol);

	/***
	 * Crea un nuevo rol.
	 * 
	 * @param Rol Nuevo rol a registrar. 
	 * @return Rol creado o existente.
	 */
	public Rol crearRol(Rol rol);

	/***
	 * Modifica el registro de un rol existente.
	 * 
	 * @param Rol a modificar. 
	 * @return Rol actualizado correctamente, si el rol no existe se crea.
	 */
	public Rol modificarRol(Rol rol);

	/***
	 * Elimina el registro de un rol.
	 * 
	 * @param idRol Identificador unico del rol a eliminar. 
	 * @return Rol eliminado o <b>null</b> si el rol no existe.
	 */
	public Rol eliminarRol(Long idRol);

	/***
	 * Consulta todos los roles registrados.
	 * 
	 * @return Listado de roles existentes o una lista vacia si no existen roles registrados.
	 */
	public List<Rol> consultarRoles();

}
