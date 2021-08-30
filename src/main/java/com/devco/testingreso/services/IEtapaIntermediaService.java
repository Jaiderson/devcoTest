package com.devco.testingreso.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.EtapaIntermedia;

@Service
public interface IEtapaIntermediaService {

	/**
	 * Busca una etapa intermedia dado su identificador unico <b>idEtapaIntermedia</b>.
	 * 
	 * @param idEtapaIntermedia Identificador unico de la etapa intermedia.
	 * @return Etapa intermedia asociada al identificador <b>idEtapaIntermedia</b> o <b>null</b> si no existe.
	 */
	public EtapaIntermedia buscarEtapaIntermediaPorId(Long idEtapaIntermedia);

	/**
	 * Busca una etapa intermedia dado el identificador unico de la etapa inicial asociada <b>idEtapaInicial</b>.
	 * 
	 * @param idEtapaInicial Identificador unico de la etapa incial.
	 * @return Etapa intermedia o <b>null</b> si no existe.
	 */
	public EtapaIntermedia buscarEtapaIntermediaPorIdEtapaInicial(Long idEtapaInicial);

	/**
	 * Busca las etapas inciales donde las calificaciones psicologica sea mayor a <b>calPsicologica</b>
	 * y tambien donde la prueba tecnica sea mayor a <b>calMedica</b>.
	 * 
	 * @param calPsicologica Calificacion psicologica.
	 * @param calMedica Calificacion medica.
	 * @return Lista las etapas intermedias o lista vacia en caso de no encontrar registros.
	 */
	public List<EtapaIntermedia> consultarEtapaIntermediaPorCalificaciones(Float calPsicologica, Float calMedica);

	/**
	 * Busca todas las etapas intermedias registradas.
	 * 
	 * @return Lista de etapas intermedias o lista vacia en caso de no encontrar registros.
	 */
	public List<EtapaIntermedia> consultarEtapaIntermedias();

	/**
	 * Registra una etapa intermedia nueva, si este ya existe retorna <b>null</b>.
	 * 
	 * @param etapaIntermedia Etapa intermedia a registrar.
	 * @return Etapa intermedia registrada o <b>null</b> si ya existe.
	 */
	public EtapaIntermedia crearEtapaIntermedia(EtapaIntermedia etapaIntermedia);

	/**
	 * Actualiza la informacion de una etapa intermedia, si no existe retorna <b>null</b>.
	 * 
	 * @param etapaIntermedia Etapa intermedia a actualizadar.
	 * @return Etapa intermedia actualizada o <b>null</b> si la etapa incial no existe.
	 */
	public EtapaIntermedia modificarEtapaIntermedia(EtapaIntermedia etapaIntermedia);

	/**
	 * Elimina el registro de una etapa intermedia dado su identificador unico <b>idEtapaIntermedia</b>,  
	 * si la etapa intermedia no existe retorna <b>null</b>.
	 * 
	 * @param idEtapaIntermedia Identificador unico de la etapa intermedia.
	 * @return EtapaIntermedia eliminada o <b>null</b> si la etapa intermedia no existe.
	 */
	public EtapaIntermedia eliminarEtapaIntermedia(Long idEtapaIntermedia);

	public List<EtapaIntermedia> etapasIntermediaSinNotificar();

}
