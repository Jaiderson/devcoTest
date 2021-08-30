package com.devco.testingreso.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.EtapaFinal;
import com.devco.testingreso.repositories.IEtapaFinalRep;
import com.devco.testingreso.services.IEtapaFinalService;

@Service
public class EtapaFinalServiceImpl implements IEtapaFinalService{

	@Autowired
	private IEtapaFinalRep etapaFinalRep;

	@Override
	public EtapaFinal buscarEtapaFinalPorId(Long idEtapaFinal) {
		EtapaFinal result = null;
		Optional<EtapaFinal> optionalEtapa = etapaFinalRep.findById(idEtapaFinal);
		if(optionalEtapa.isPresent()) {
			result = optionalEtapa.get();
		}
		return result;
	}

	@Override
	public EtapaFinal buscarEtapaFinalPorIdEtapaIntermedia(Long idEtapaIntermedia) {
		return etapaFinalRep.findByIdEtapaIntermedia(idEtapaIntermedia);
	}

	@Override
	public List<EtapaFinal> consultarEtapaFinalPorPromedio(Float promedio) {
		return etapaFinalRep.findByPuntajePromedio(promedio);
	}

	@Override
	public List<EtapaFinal> consultarEtapaFinales() {
		return etapaFinalRep.findAll();
	}

	@Override
	public EtapaFinal crearEtapaFinal(EtapaFinal etapaFinal) {
		EtapaFinal etapaFinalDb = etapaFinalRep.findByIdEtapaIntermedia(etapaFinal.getEtapaIntermedia().getIdEtapaM());
		if(null == etapaFinalDb) {
			return etapaFinalRep.save(etapaFinal);
		}
		return null;
	}

	@Override
	public EtapaFinal modificarEtapaFinal(EtapaFinal etapaFinal) {
		EtapaFinal etapaFinalDb = buscarEtapaFinalPorId(etapaFinal.getIdEtapaF());
		if(null != etapaFinalDb) {
			etapaFinalDb = etapaFinalRep.save(etapaFinal);
		}
		return etapaFinalDb;
	}

	@Override
	public EtapaFinal eliminarEtapaFinal(Long idEtapaFinal) {
		EtapaFinal etapaFinalDb = buscarEtapaFinalPorId(idEtapaFinal);
		if(null != etapaFinalDb) {
			etapaFinalRep.delete(etapaFinalDb);
		}
		return etapaFinalDb;
	}

	@Override
	public List<EtapaFinal> etapasFinalesSinNotificar() {
		return etapaFinalRep.findEtapaFinalNoEnviadas();
	}

}
