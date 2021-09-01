package com.devco.testingreso.servicetests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devco.testingreso.entities.Rol;
import com.devco.testingreso.repositories.IRolRep;
import com.devco.testingreso.services.IRolService;
import com.devco.testingreso.services.implementations.RolServiceImpl;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RolServiceTest {

	@Autowired
	private IRolRep rolRep;
	
	private IRolService rolService;

	@BeforeEach
	public void init() {
		rolService = new RolServiceImpl(rolRep);
	}

	@Test
	void buscarRolTest() {
		List<Rol> roles = rolRep.findAll();
		assertThat(roles).isNotNull().isNotEmpty();
	}

	@Test
	void crearRolTest() {
		Rol rol = Rol.builder().nombre("Scrum Product Owner").build();
		rol = rolService.crearRol(rol);
		assertThat(rol).isNotNull();
		assertThat(rol.getIdRol()).isPositive();
	}

	@Test
	void modificarRolTest() {
		Rol rol = Rol.builder().nombre("Scrum Product Owner").build();
		rol = rolService.crearRol(rol);
		assertThat(rol).isNotNull();
		assertThat(rol.getIdRol()).isPositive();
		
		rol.setNombre("Scrum Product Leader");
		rol = rolService.modificarRol(rol);
		assertThat(rol).isNotNull();
		assertThat(rol.getNombre()).isEqualTo("Scrum Product Leader");
	}

	@Test
	void eliminarRolTest() {
		Rol rol = Rol.builder().nombre("Scrum Product Owner").build();
		rol = rolService.crearRol(rol);
		assertThat(rol).isNotNull();
		assertThat(rol.getIdRol()).isPositive();
		
		rolService.eliminarRol(rol.getIdRol());
		rol = rolService.buscarRol(rol.getIdRol());
		assertThat(rol).isNull();
	}

}
