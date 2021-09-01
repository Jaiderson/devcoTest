package com.devco.testingreso.controllertests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.devco.testingreso.controllers.RolController;
import com.devco.testingreso.entities.Rol;
import com.devco.testingreso.services.IRolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RolController.class)
class RolTest {

	@MockBean
	private IRolService rolService;

	@Test
	void crearRolTest() throws JsonProcessingException {
		Rol newRol = Rol.builder().nombre("Desarrollador").build();
		System.out.println(newRol);
		Rol savedRol = Rol.builder().idRol(1L).nombre("Desarrollador").build();
		System.out.println(savedRol);
		
		ObjectMapper oMapper = new ObjectMapper();
				
		String json = oMapper.writeValueAsString(newRol);
		System.out.println(json);

	}
}
