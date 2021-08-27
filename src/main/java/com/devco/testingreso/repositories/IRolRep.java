package com.devco.testingreso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devco.testingreso.entities.Rol;

@Repository
public interface IRolRep extends JpaRepository<Rol, Long>{

	/**
	 * Busca un Rol dado su identificador unico <b>idRol</b>.
	 * 
	 * @param idRol Identificador unico del rol.
	 * @return Rol asociado al <b>idRol</b> o <b>null</b> en caso de no existir.
	 */
	public Rol findByIdRol(Long idRol);

	/**
	 * Busca un Rol dado su nombre <b>nombreRol</b>.
	 * 
	 * @param nombreRol Nombre Nombre unico del rol.
	 * @return Rol asociado al <b>idRol</b> o <b>null</b> en caso de no existir.
	 */
	public Rol findByNombre(String nombreRol);

}
