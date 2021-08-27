package com.devco.testingreso.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devco.testingreso.entities.Candidato;
import com.devco.testingreso.entities.Rol;

@Repository
public interface ICandidatoRep extends JpaRepository<Candidato, Long>{

	/**
	 * Busca un candidato dado su <b>idCondidato</b> Identificador unico.
	 * 
	 * @param idCondidato Identificador unico del candidato.
	 * @return Candidato asociado al <b>idCondidato</b> o <b>null</b> en caso que no exista.
	 */
	public Candidato findByIdCandidato(Long idCondidato);

	/**
	 * Busca un candidato dado DNI Numero de documento de identidad <b>dni</b>.
	 * 
	 * @param dni Numero de documento de identidad
	 * @return Candidarto asociado al <b>dni</b> o <b>null</b> en caso de no existir.
	 */
	public Candidato findByDni(String dni);

	/**
	 * Busca candidatos por <b>nombre</b>. 
	 * 
	 * @param nombre Nombre a buscar.
	 * @return Lista de candidatos con el <b>nombre</b> o lista vacia en caso de no encontrar coincidencias.
	 */
	public List<Candidato> findByNombre(String nombre);

	/**
	 * Busca todos los candidatos que tengan el <b>rol</b> asociado. 
	 * 
	 * @param rol Rol a buscar.
	 * @return Lista de candidatos con el <b>rol</b> o lista vacia en caso de no encontrar coincidencias.
	 */
	public List<Candidato> findByRol(Rol rol);

}
