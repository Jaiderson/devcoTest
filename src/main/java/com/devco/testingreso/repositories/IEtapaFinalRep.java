package com.devco.testingreso.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devco.testingreso.entities.EtapaFinal;

@Repository
public interface IEtapaFinalRep extends JpaRepository<EtapaFinal, Long>{

	/**
	 * Busca una etapa final dado su identificador unico <b>idEtapaIntermedia</b> asociado.
	 * 
	 * @param idEtapaIntermedia Idetificador unico de la etapa intermedia.
	 * @return Etapa Final o null si no existe.
	 */
	@Query(value="select * from etapa_final where idetapaM = :idEtapaIntermedia ", nativeQuery = true)
	public EtapaFinal findByIdEtapaIntermedia(@Param("idEtapaIntermedia") Long idEtapaIntermedia);

	/**
	 * Busca las etapas finales cuyos candiatos hallan sacado mas de <b>calPsicologica</b> en su
	 * calificacion psicologica y mas de <b>calMedica</b> en su calificacion medica.
	 * 
	 * @param calPsicologica Valor calificacion prueba psicologica.
	 * @param calMedica Valor calificacion prueba medica.
	 * @return Lista etapas intermedias o lista vacia si no encuentra registros.
	 */
	@Query(value="select * from etapa_final where puntaje_promedio >= :calPsicologica "
			   + " and calificacion_medica >= :promedio ", nativeQuery = true)
	public List<EtapaFinal> findByPuntajePromedio(@Param("promedio") Float promedio);

	@Query(value="select * from etapa_final where estado_mail <> 'SI' or estado_mail is null", nativeQuery = true)
	public List<EtapaFinal> findEtapaFinalNoEnviadas();

}
