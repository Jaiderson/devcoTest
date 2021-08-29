package com.devco.testingreso.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devco.testingreso.entities.EtapaInicial;

@Repository
public interface IEtapaInicialRep extends JpaRepository<EtapaInicial, Long>{

	/**
	 * Busca una etapa inicial dado su identificador único <b>idEtapa</b>.
	 * 
	 * @param idEtapa Idetificador unico de la etapa inicial.
	 * @return Etapa inicial o null si no existe.
	 */
	public EtapaInicial findByIdEtapa(Long idEtapa);

	/**
	 * Busca las etapas iniciales de un cadidato dado su DNI.
	 * 
	 * @param dni Numero de documento de identidad del candidato.
	 * @return Lista de etapas iniciales o lista vacia si no encuentra registros.
	 */
	@Query(value="select ei.* from etapa_inicial ei inner join candidatos c on (ei.idcandidato = c.idcandidato) "
			   + "where c.dni = :dniCandidato ", nativeQuery = true)
	public List<EtapaInicial> findByDniCandidato(@Param("dniCandidato") String dniCandidato);

	/**
	 * Busca el listado de etapas iniciales resgitradas en la fehca <b>fechaPostulacion</b>.
	 *  
	 * @param fechaPostulacion Fecha de postulacion.
	 * @return Listado de estapas iniciales o lista vacia si no encuantra registros.
	 */
	@Query(value="select * from etapa_inicial e where e.fecha_postulacion = :fechaPostulacion ", nativeQuery = true)
	public List<EtapaInicial> findByFechaPostulacion(@Param("fechaPostulacion") Date fechaPostulacion);

	/**
	 * Busca el listado de etapas iniciales resgitradas entre las fehcas <b>fechaPostulacionIni - fechaPostulacionFin</b>.
	 *  
	 * @param fechaPostulacionIni Fecha de postulacion inicial.
	 * @param fechaPostulacionFin Fecha de postulacion final.
	 * @return Listado de estapas iniciales o lista vacia si no encuantra registros.
	 */
	@Query(value="select * from etapa_inicial e where e.fecha_postulacion >= :fechaPostulacionIni "
			   + "and e.fecha_postulacion <= :fechaPostulacionFin", nativeQuery = true)
	public List<EtapaInicial> findBetweenFechaPostulacion(@Param("fechaPostulacionIni") Date fechaPostulacionIni,
			                                              @Param("fechaPostulacionFin") Date fechaPostulacionFin);

	/**
	 * Busca el listado de etapas iniciales resgitradas para el rol con id <b>idRol</b> entre las fehcas <b>fechaPostulacionIni - fechaPostulacionFin</b>.
	 *  
	 * @param idRol Identificacor del rol.
	 * @param fechaPostulacionIni Fecha de postulacion inicial.
	 * @param fechaPostulacionFin Fecha de postulacion final.
	 * @return Listado de estapas iniciales o lista vacia si no encuantra registros.
	 */
	@Query(value="select ei.* from etapa_inicial ei inner join candidatos c on (ei.idcandidato = c.idcandidato) "
			   + "where ei.fecha_postulacion >= :fechaPostulacionIni "
			   + "  and ei.fecha_postulacion <= :fechaPostulacionFin "
			   + "  and c.idrol = :idRol", nativeQuery = true)
	public List<EtapaInicial> findBetweenFechaPostulacionRol(@Param("idRol") Long idRol,
														     @Param("fechaPostulacionIni") Date fechaPostulacionIni, 
			                                                 @Param("fechaPostulacionFin") Date fechaPostulacionFin);

	/**
	 * Busca el listado de etapas iniciales resgitradas para el rol con id <b>idRol</b> cuya calificacion tecnica sea mayo a <b>calTecnica</b> 
	 * entre las fehcas <b>fechaPostulacionIni - fechaPostulacionFin</b>.
	 *  
	 * @param idRol Identificacor del rol.
	 * @param calTecnica Valor de la calificacion tecnica.
	 * @param fechaPostulacionIni Fecha de postulacion inicial.
	 * @param fechaPostulacionFin Fecha de postulacion final.
	 * @return Listado de estapas iniciales o lista vacia si no encuantra registros.
	 */
	@Query(value="select ei.* from etapa_inicial ei inner join candidatos c on (ei.idcandidato = c.idcandidato) "
			   + "where ei.fecha_postulacion >= :fechaPostulacionIni "
			   + "  and ei.fecha_postulacion <= :fechaPostulacionFin "
			   + "  and c.idrol = :idRol "
			   + "  and ei.calificacion_tecnica >= :calTecnica", nativeQuery = true)
	public List<EtapaInicial> findBetweenFechaPostulacionRolCalTecnica(@Param("idRol") Long idRol,
                                                                       @Param("calTecnica") Float calTecnica,
                                                                       @Param("fechaPostulacionIni") Date fechaPostulacionIni, 
                                                                       @Param("fechaPostulacionFin") Date fechaPostulacionFin);

	/**
	 * Busca el listado de etapas iniciales resgitradas para el rol con id <b>idRol</b> cuya calificacion teorica sea mayo a <b>calTeorica</b> 
	 * entre las fehcas <b>fechaPostulacionIni - fechaPostulacionFin</b>.
	 *  
	 * @param idRol Identificacor del rol.
	 * @param calTeorica Valor de la calificacion teorica.
	 * @param fechaPostulacionIni Fecha de postulacion inicial.
	 * @param fechaPostulacionFin Fecha de postulacion final.
	 * @return Listado de estapas iniciales o lista vacia si no encuantra registros.
	 */
	@Query(value="select ei.* from etapa_inicial ei inner join candidatos c on (ei.idcandidato = c.idcandidato) "
			   + "where ei.fecha_postulacion >= :fechaPostulacionIni "
			   + "  and ei.fecha_postulacion <= :fechaPostulacionFin "
			   + "  and c.idrol = :idRol "
			   + "  and ei.calificacion_teorica >= :calTeorica", nativeQuery = true)
	public List<EtapaInicial> findBetweenFechaPostulacionRolCalTeorica(@Param("idRol") Long idRol,
                                                                       @Param("calTeorica") Float calTeorica,
                                                                       @Param("fechaPostulacionIni") Date fechaPostulacionIni, 
                                                                       @Param("fechaPostulacionFin") Date fechaPostulacionFin);

}
