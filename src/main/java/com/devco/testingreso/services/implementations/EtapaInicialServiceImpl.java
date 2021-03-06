package com.devco.testingreso.services.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.EtapaInicial;
import com.devco.testingreso.repositories.IEtapaInicialRep;
import com.devco.testingreso.services.IEtapaInicialService;

@Service
public class EtapaInicialServiceImpl implements IEtapaInicialService{

	@Autowired
	private IEtapaInicialRep etapaInicialRep;

	@Override
	public EtapaInicial buscarEtapaInicialPorId(Long idEtapaInicial) {
		return etapaInicialRep.findByIdEtapa(idEtapaInicial);
	}

	@Override
	public List<EtapaInicial> consultarEtapaInicialsPorDni(String dni) {
		return etapaInicialRep.findByDniCandidato(dni);
	}

	@Override
	public List<EtapaInicial> consultarEtapaIniciales() {
		return etapaInicialRep.findAll();
	}

	@Override
	public EtapaInicial crearEtapaInicial(EtapaInicial etapaInicial) {
		return etapaInicialRep.save(etapaInicial);
	}

	@Override
	public EtapaInicial modificarEtapaInicial(EtapaInicial etapaInicial) {
		EtapaInicial etapaInicialDb = buscarEtapaInicialPorId(etapaInicial.getIdEtapa());
		if(etapaInicialDb != null) {
			etapaInicialDb = etapaInicialRep.save(etapaInicial);
		}
		return etapaInicialDb;
	}

	@Override
	public EtapaInicial eliminarEtapaInicial(Long idEtapaInicial) {
		EtapaInicial etapaInicialDb = etapaInicialRep.findByIdEtapa(idEtapaInicial);
		if(etapaInicialDb != null) {
			etapaInicialRep.delete(etapaInicialDb);
		}
		return etapaInicialDb;
	}

	@Override
	public List<EtapaInicial> consultarEtapaInicialesPorFecPostulacion(Date fecPostulacion) {
		return etapaInicialRep.findByFechaPostulacion(fecPostulacion);
	}

	@Override
	public List<EtapaInicial> consultarEtapaInicialesEntreFecPostulacion(Date fecPostulacionIni, Date fecPostulacionFin) {
		return etapaInicialRep.findBetweenFechaPostulacion(fecPostulacionIni, fecPostulacionFin);
	}

	@Override
	public List<EtapaInicial> consultarEtapaInicialesPorRolEntreFecPostulacion(Long idRol, Date fecPostulacionIni,
			Date fecPostulacionFin) {
		return etapaInicialRep.findBetweenFechaPostulacionRol(idRol, fecPostulacionIni, fecPostulacionFin);
	}

	@Override
	public List<EtapaInicial> consultarEtapasEntreFechaPostulacionRolCalTecnica(Long idRol, Float calTecnica,
			Date fecPostulacionIni, Date fecPostulacionFin) {
		return etapaInicialRep.findBetweenFechaPostulacionRolCalTecnica(idRol, calTecnica, fecPostulacionIni, fecPostulacionFin);
	}

	@Override
	public List<EtapaInicial> consultarEtapasEntreFechaPostulacionRolCalTeorica(Long idRol, Float calTeorica,
			Date fecPostulacionIni, Date fecPostulacionFin) {
		return etapaInicialRep.findBetweenFechaPostulacionRolCalTeorica(idRol, calTeorica, fecPostulacionIni, fecPostulacionFin);
	}

	@Override
	public List<EtapaInicial> etapasInicialesSinNotificar() {
		return etapaInicialRep.findEtapaInicialNoEnviadas();
	}

}
