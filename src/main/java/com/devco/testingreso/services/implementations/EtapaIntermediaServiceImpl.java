package com.devco.testingreso.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.EtapaIntermedia;
import com.devco.testingreso.repositories.IEtapaIntermediaRep;
import com.devco.testingreso.services.IEtapaIntermediaService;

@Service
public class EtapaIntermediaServiceImpl implements IEtapaIntermediaService{

	@Autowired
	private IEtapaIntermediaRep etapaIntermediaRep;

	@Override
	public EtapaIntermedia buscarEtapaIntermediaPorId(Long idEtapaIntermedia) {
		EtapaIntermedia result = null;
		Optional<EtapaIntermedia> optionalEtapa = etapaIntermediaRep.findById(idEtapaIntermedia);
		if(optionalEtapa.isPresent()) {
			result = optionalEtapa.get();
		}
		return result;
	}

	@Override
	public EtapaIntermedia buscarEtapaIntermediaPorIdEtapaInicial(Long idEtapaInicial) {
		return etapaIntermediaRep.findByIdEtapaInicial(idEtapaInicial);
	}

	@Override
	public List<EtapaIntermedia> consultarEtapaInicialPorCalificaciones(Float calPsicologica, Float calMedica) {
		return etapaIntermediaRep.findByCalificaciones(calPsicologica, calMedica);
	}

	@Override
	public List<EtapaIntermedia> consultarEtapaIntermedias() {
		return etapaIntermediaRep.findAll();
	}

	@Override
	public EtapaIntermedia crearEtapaIntermedia(EtapaIntermedia etapaIntermedia) {
		EtapaIntermedia etapaIntermediaDb = etapaIntermediaRep.findByIdEtapaInicial(etapaIntermedia.getEtapaInicial().getIdEtapa());
		if(null == etapaIntermediaDb) {
			return etapaIntermediaRep.save(etapaIntermedia);
		}
		return null;
	}

	@Override
	public EtapaIntermedia modificarEtapaIntermedia(EtapaIntermedia etapaIntermedia) {
		EtapaIntermedia etapaIntermediaDb = etapaIntermediaRep.findByIdEtapaInicial(etapaIntermedia.getEtapaInicial().getIdEtapa());
		if(null != etapaIntermediaDb) {
			etapaIntermedia = etapaIntermediaRep.save(etapaIntermedia);
		}
		return etapaIntermedia;
	}

	@Override
	public EtapaIntermedia eliminarEtapaIntermedia(Long idEtapaIntermedia) {
		EtapaIntermedia etapaIntermediaDb = buscarEtapaIntermediaPorId(idEtapaIntermedia);
		if(null != etapaIntermediaDb) {
			etapaIntermediaRep.delete(etapaIntermediaDb);
		}
		return etapaIntermediaDb;
	}

}
