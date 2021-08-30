package com.devco.testingreso.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.EtapaFinal;

@Service
public interface IEtapaFinalService {

	/**
	 * Busca una etapa final dado su identificador unico <b>idEtapaFinal</b>.
	 * 
	 * @param idEtapaFinal Identificador unico de la etapa final.
	 * @return Etapa final o <b>null</b> si no existe.
	 */
	public EtapaFinal buscarEtapaFinalPorId(Long idEtapaFinal);

	/**
	 * Busca una etapa final dado el identificador unico de la etapa intermedia asociada <b>idEtapaIntermedia</b>.
	 * 
	 * @param idEtapaIntermedia Identificador unico de la etapa intermedia.
	 * @return Etapa intermedia o <b>null</b> si no existe.
	 */
	public EtapaFinal buscarEtapaFinalPorIdEtapaIntermedia(Long idEtapaIntermedia);

	/**
	 * Busca las etapas finales donde el valor del promedio sea mayor a  psicologica sea mayor a <b>promedio</b>.
	 * 
	 * @param promedio Calificacion promedio del proceso.
	 * @return Lista las etapas finales o lista vacia en caso de no encontrar registros.
	 */
	public List<EtapaFinal> consultarEtapaFinalPorPromedio(Float promedio);

	/**
	 * Busca todas las etapas finales registradas.
	 * 
	 * @return Lista de etapas finales o lista vacia en caso de no encontrar registros.
	 */
	public List<EtapaFinal> consultarEtapaFinales();

	/**
	 * Registra una nueva etapa final, si este ya existe retorna <b>null</b>.
	 * 
	 * @param etapaFinal Etapa final a registrar.
	 * @return Etapa final registrada o <b>null</b> si ya existe.
	 */
	public EtapaFinal crearEtapaFinal(EtapaFinal etapaFinal);

	/**
	 * Actualiza la informacion de una etapa final, si no existe retorna <b>null</b>.
	 * 
	 * @param etapaFinal Etapa final a actualizadar.
	 * @return Etapa final actualizada o <b>null</b> si la etapa incial no existe.
	 */
	public EtapaFinal modificarEtapaFinal(EtapaFinal etapaFinal);

	/**
	 * Elimina el registro de una etapa final dado su identificador unico <b>idEtapaFinal</b>,  
	 * si la etapa final no existe retorna <b>null</b>.
	 * 
	 * @param idEtapaFinal Identificador unico de la etapa final.
	 * @return Etapa final eliminada o <b>null</b> si la etapa intermedia no existe.
	 */
	public EtapaFinal eliminarEtapaFinal(Long idEtapaFinal);

	public List<EtapaFinal> etapasFinalesSinNotificar();
}
