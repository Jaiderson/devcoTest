package com.devco.testingreso.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.EtapaInicial;

@Service
public interface IEtapaInicialService {

	/**
	 * Busca un etapa incial dado su identificador unico <b>idEtapaInicial</b>.
	 * 
	 * @param idEtapaInicial Identificador unico de la etapa incial.
	 * @return EtapaInicial Etapa incial asociada al identificador <b>idEtapaInicial</b> o <b>null</b> si no existe.
	 */
	public EtapaInicial buscarEtapaInicialPorId(Long idEtapaInicial);

	/**
	 * Busca las etapas inciales de un candidato dado su <b>dni</b>.
	 * 
	 * @param dni Numero de documento de identidad del candidato. (C92539456)
	 * @return Lista las etapas inciales o lista vacia en caso de no encontrar ninguno.
	 */
	public List<EtapaInicial> consultarEtapaInicialsPorDni(String dni);

	/**
	 * Busca todas las etapas inciales registradas.
	 * 
	 * @return Lista de candidatos o lista vacia en caso de no encontrar ninguno.
	 */
	public List<EtapaInicial> consultarEtapaIniciales();

	/**
	 * Registra una etapa incial nueva, si este ya existe retorna <b>null</b>.
	 * 
	 * @param etapaInicial EtapaInicial a registrar.
	 * @return Etapa inicial registrada o <b>null</b> si ya existe.
	 */
	public EtapaInicial crearEtapaInicial(EtapaInicial etapaInicial);

	/**
	 * Actualiza la informacion de una etapa incial, si este no existe retorna <b>null</b>.
	 * 
	 * @param etapaInicial con la informacion de la etapa inicial actualizada.
	 * @return EtapaInicial actualizado o <b>null</b> si la etapa incial no existe.
	 */
	public EtapaInicial modificarEtapaInicial(EtapaInicial etapaInicial);

	/**
	 * Elimina el registro de una etapa inicial dado su identificador unico <b>idEtapaInicial</b>, 
	 * si la etapa inicial no existe retorna <b>null</b>.
	 * 
	 * @param idEtapaInicial Identificador unico de la etapa inicial.
	 * @return EtapaInicial eliminada o <b>null</b> si la etapa inicial no existe.
	 */
	public EtapaInicial eliminarEtapaInicial(Long idEtapaInicial);

}
