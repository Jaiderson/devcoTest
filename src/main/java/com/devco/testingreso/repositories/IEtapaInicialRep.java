package com.devco.testingreso.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devco.testingreso.entities.Candidato;
import com.devco.testingreso.entities.EtapaInicial;

@Repository
public interface IEtapaInicialRep extends JpaRepository<EtapaInicial, Long>{

	/**
	 * Busca una etapa inicial dado su identificador único <b>idEtapa</b>.
	 * 
	 * @param idEtapa Idetificador unico de la etapa inicial.
	 * @return Etapa inicial o null si no existe.yout
	 */
	public EtapaInicial findByIdEtapa(Long idEtapa);

	public List<EtapaInicial> findByCandidato(Candidato idCandidato);

	@Query(value="select * from EtapaInicial e where e.fecPostulacion = :fechaPostulacion ", nativeQuery = true)
	public List<EtapaInicial> findByFechaPostulacion(@Param("fechaPostulacion") Date fechaPostulacion);

	/*	
	@Query(value="select * from EtapaInicial e where e.fecPostulacion between :fechaPostulacionIni and :fecPostulacionFin", nativeQuery = true)
    public List<EtapaInicial> findByFechaPostulacion(@Param("fecPostulacionIni") Date fecPostulacionIni, @Param("fecPostulacionFin") Date fecPostulacionFin);   
	
   @Query(value="select * from EtapaInicial e where e.fecPostulacion = :fechaPostulacion and "
			   + "idRol = :idRol and calificacionTeoria = :calTeorica ", nativeQuery = true)
	public List<EtapaInicial> findByFecPostulacionRolCalTeorica(Date fechaPostulacion, Long idRol, float calTeorica);

	@Query(value="select * from EtapaInicial e where e.fecPostulacion = :fechaPostulacion and "
			   + "idRol = :idRol and calificacionTecnica = :calTecnica ", nativeQuery = true)
	public List<EtapaInicial> findByFecPostulacionRolCalTecnica(Date fechaPostulacion, Rol rol, float calTecnica);

	@Query(value="select * from EtapaInicial e where e.fecPostulacion = :fechaPostulacion and "
			   + "idRol = :idRol and calificacionTeoria= :calTeorica and calificacionTecnica = :calTecnica ", nativeQuery = true)
	public List<EtapaInicial> findByFecPostulacionRolCalTeoricaCalTecnica(Date fechaPostulacion, Rol rol, float calTeorica, float calTecnica);
*/
}
