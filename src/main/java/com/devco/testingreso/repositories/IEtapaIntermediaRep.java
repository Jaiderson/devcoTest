package com.devco.testingreso.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devco.testingreso.entities.EtapaIntermedia;

@Repository
public interface IEtapaIntermediaRep extends JpaRepository<EtapaIntermedia, Long>{

	/**
	 * Busca una etapa intermedia dado su identificador unico <b>idEtapa</b>.
	 * 
	 * @param idEtapaInicial Idetificador unico de la etapa inicial.
	 * @return Etapa inicial o null si no existe.
	 */
	@Query(value="select * from etapa_intermedia where idetapa = :idEtapaInicial ", nativeQuery = true)
	public EtapaIntermedia findByIdEtapaInicial(@Param("idEtapaInicial") Long idEtapaInicial);

	/**
	 * Busca las etapas intermedias cuyos candiatos hallan sacado mas de <b>calPsicologica</b> en su
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

	@Query(value="select * from etapa_intermedia where estado_mail <> 'SI' or estado_mail is null", nativeQuery = true)
	public List<EtapaIntermedia> findEtapaIntermediaNoEnviadas();

}
