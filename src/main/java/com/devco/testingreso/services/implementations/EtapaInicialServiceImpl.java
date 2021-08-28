package com.devco.testingreso.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.Candidato;
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
		return etapaInicialRep.findByCandidato(Candidato.builder().dni(dni).build());
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
		EtapaInicial etapaInicialDb = etapaInicialRep.findByIdEtapa(etapaInicial.getIdEtapa());
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

}
