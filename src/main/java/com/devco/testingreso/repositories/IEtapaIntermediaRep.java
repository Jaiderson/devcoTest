package com.devco.testingreso.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devco.testingreso.entities.EtapaIntermedia;

@Repository
public interface IEtapaIntermediaRep extends JpaRepository<EtapaIntermedia, Long>{

	/**
	 * Busca una etapa inicial dado su identificador único <b>idEtapa</b>.
	 * 
	 * @param idEtapa Idetificador unico de la etapa inicial.
	 * @return Etapa inicial o null si no existe.
	 */
	@Query(value="select * from etapa_intermedia where idetapa = :idEtapaInicial ", nativeQuery = true)
	public EtapaIntermedia findByIdEtapaInicial(@Param("idEtapaInicial") Long idEtapaInicial);

	/**
	 * Busca las etapas iniciales cuyos candiatos hallan sacado mas de <b>calPsicologica</b> en su
	 * calificacion psicologica y mas de <b>calMedica</b> en su calificacion medica.
	 * 
	 * @param calPsicologica Valor calificacion prueba psicologica.
	 * @param calMedica Valor calificacion prueba medica.
	 * @return Lista etapas intermedias o lista vacia si no encuentra registros.
	 */
	@Query(value="select * from etapa_intermedia where calificacion_psicologica >= :calPsicologica "
			   + " and calificacion_medica >= :calMedica ", nativeQuery = true)
	public List<EtapaIntermedia> findByCalificaciones(@Param("calPsicologica") Float calPsicologica, 
			                                          @Param("calMedica") Float calMedica);

}
