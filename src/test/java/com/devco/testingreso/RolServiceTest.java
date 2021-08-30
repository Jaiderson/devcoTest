package com.devco.testingreso;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devco.testingreso.entities.Rol;
import com.devco.testingreso.repositories.IRolRep;
import com.devco.testingreso.services.IRolService;
import com.devco.testingreso.services.implementations.RolServiceImpl;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RolServiceTest {

	@Mock
	private IRolRep rolRep;
	
	private IRolService rolService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		rolService = new RolServiceImpl(rolRep);
		
		Rol rol = Rol.builder().idRol(10L).nombre("Desarrollador").build();
		Mockito.when(rolRep.findByIdRol(10L)).thenReturn(rol);
	}

	@Test
	public void buscarRolTest() {
		Rol rol = rolRep.findByIdRol(10L);
		Assertions.assertThat(rol.getNombre().equals("Desarrollador"));
	}

	@Test
	public void modificarRolTest() {
		Rol rol = Rol.builder().idRol(-10L).nombre("Scrum Product Owner").build();
		rol = rolService.modificarRol(rol);
		Assertions.assertThat(null == rol);
	}

}
