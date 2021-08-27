package com.devco.testingreso.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.Candidato;

@Service
public interface ICandidatoService {

	/**
	 * Busca un cadidato dado su identificador unico <b>idCandidato</b>.
	 * 
	 * @param idCandidato Identificador unico del candidato.
	 * @return Candidato asociado al identificador <b>idCandidato</b>.
	 */
	public Candidato buscarCandidatoPorId(Long idCandidato);

	/**
	 * Busca un cadidato dado su numero de documento de identidad  <b>dni</b>.
	 * 
	 * @param dni Numero de documento de identidad del candidato.
	 * @return Candidato asociado al numero de documento de identidad <b>dni</b>.
	 */
	public Candidato buscarCandidatoPorDni(String dni);

	/**
	 * Busca los candidatos cuyo nombre contenga <b>nomCandidato</b>
	 * 
	 * @param nomCandidato Nombre de los candidatos a buscar.
	 * @return Lista de candidatos o lista vacia en caso de no encontrar ninguno.
	 */
	public List<Candidato> consultarCandidatosPorNombre(String nomCandidato);

	/**
	 * Busca todos los candidatos registrado.
	 * 
	 * @return Lista de candidatos o lista vacia en caso de no encontrar ninguno.
	 */
	public List<Candidato> consultarCandidatos();

	/**
	 * Registra un candidato nuevo, si este ya existe retorna el existente.
	 * 
	 * @param candidato Candidato a registrar.
	 * @return Candidato nuevo o existente.
	 */
	public Candidato crearCandidato(Candidato candidato);

	/**
	 * Actualiza la informacion de un candidato, si este no existe retorna <b>null</b>.
	 * 
	 * @param candidato con la informacion del candidato actualizada.
	 * @return Candidato actualizado o <b>null</b> si el candidato no existe.
	 */
	public Candidato modificarCandidato(Candidato candidato);

	/**
	 * Elimina el registro de un candidato dado su identificador unico <b>idCandidato</b>, si el candidato no existe retorna <b>null</b>.
	 * 
	 * @param idCandidato Identificador unico del candidato
	 * @return Candidato eliminado o <b>null</b> si el candidato no existe.
	 */
	public Candidato eliminarCandidato(Long idCandidato);

	/**
	 * Elimina el registro de un candidato dado su DNI <b>dniCandidato</b>, si el candidato no existe retorna <b>null</b>.
	 * 
	 * @param dniCandidato Numero de documento de identidad del candidato
	 * @return Candidato eliminado o <b>null</b> si el candidato no existe.
	 */
	public Candidato eliminarCandidato(String dniCandidato);

}
