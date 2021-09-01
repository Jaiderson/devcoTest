package com.devco.testingreso.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.Candidato;
import com.devco.testingreso.repositories.ICandidatoRep;
import com.devco.testingreso.services.ICandidatoService;

@Service
public class CandidatoServiceImpl implements ICandidatoService{

	public CandidatoServiceImpl(ICandidatoRep candidatoRep) {
		this.candidatorep = candidatoRep;
	}

	@Autowired
	private ICandidatoRep candidatorep;

	@Override
	public Candidato buscarCandidatoPorId(Long idCandidato) {
		return candidatorep.findByIdCandidato(idCandidato);
	}

	@Override
	public Candidato buscarCandidatoPorDni(String dni) {
		return candidatorep.findByDni(dni);
	}

	@Override
	public List<Candidato> consultarCandidatosPorNombre(String nomCandidato) {
		return candidatorep.buscarPorNombre(nomCandidato);
	}

	@Override
	public List<Candidato> consultarCandidatos() {
		return candidatorep.findAll();
	}

	@Override
	public Candidato crearCandidato(Candidato candidato) {
		Candidato newCandidato = candidatorep.findByDni(candidato.getDni());
		if(null == newCandidato) {
			newCandidato = candidatorep.save(candidato);
		}
		newCandidato.setExistente(true);
		return newCandidato;
	}

	@Override
	public Candidato modificarCandidato(Candidato candidato) {
		Candidato newCandidato = candidatorep.findByDni(candidato.getDni());
		if(null != newCandidato) {
			newCandidato = candidatorep.save(candidato);
		}
		return newCandidato;
	}

	@Override
	public Candidato eliminarCandidato(Long idCandidato) {
		Candidato candidato = candidatorep.findByIdCandidato(idCandidato);
		if(null != candidato) {
			candidatorep.delete(candidato);
		}
		return candidato;
	}

	@Override
	public Candidato eliminarCandidato(String dniCandidato) {
		Candidato candidato = candidatorep.findByDni(dniCandidato);
		if(null != candidato) {
			candidatorep.delete(candidato);
		}
		return candidato;	
	}

}
