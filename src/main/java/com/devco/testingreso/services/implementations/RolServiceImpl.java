package com.devco.testingreso.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devco.testingreso.entities.Rol;
import com.devco.testingreso.repositories.IRolRep;
import com.devco.testingreso.services.IRolService;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	private IRolRep rolRep;
	
	@Override
	public Rol buscarRol(Long idRol) {
		return rolRep.findByIdRol(idRol);
	}

	@Override
	public Rol crearRol(Rol rol) {
		Rol newRol = rolRep.findByNombre(rol.getNombre());
		if(null == newRol) {
			newRol = rolRep.save(rol);
		}
		newRol.setExistente(true);
		return newRol;
	}

	@Override
	public Rol modificarRol(Rol rol) {
		Rol newRol = rolRep.findByIdRol(rol.getIdRol());
		if(null != newRol) {
			newRol = rolRep.save(rol);
		}
		return newRol;
	}

	@Override
	public Rol eliminarRol(Long idRol) {
		Rol rol = rolRep.findByIdRol(idRol);
		if(null != rol) {
			rolRep.delete(rol);
		}
		return rol;
	}

	@Override
	public List<Rol> consultarRoles() {
		return rolRep.findAll();
	}

}
