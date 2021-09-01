package com.devco.testingreso.controllertests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.devco.testingreso.controllers.RolController;
import com.devco.testingreso.entities.Rol;
import com.devco.testingreso.services.IRolService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;


@WebMvcTest(RolController.class)
class RolControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;	
	
	@MockBean
	private IRolService rolService;

	@BeforeEach
	public void init() {
		this.objectMapper = new ObjectMapper();
	}

	@Test
	@WithMockUser(username = "admin", roles = {"USER","ADMIN"})
	void listarRolesTest() throws Exception {
		Rol rol = Rol.builder().idRol(1L).nombre("Desarrollador").build();
		List<Rol> roles = Lists.newArrayList();
		roles.add(rol);
		
		Mockito.when(rolService.consultarRoles()).thenReturn(roles);

		mockMvc.perform(get("/roles").contentType(MediaType.APPLICATION_JSON)
									 .with(csrf())
				        ).andDo(print())
		                 .andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", roles = {"USER","ADMIN"})
	void crearRolTest() throws Exception {
		Rol newRol = Rol.builder().nombre("Desarrollador").build();
		Rol savedRol = Rol.builder().idRol(1L).nombre("Desarrollador").build();
		Mockito.when(rolService.crearRol(newRol)).thenReturn(savedRol);
		String json = objectMapper.writeValueAsString(newRol);
		System.out.println(json);

		mockMvc.perform(post("/roles").contentType(MediaType.APPLICATION_JSON)
				                 .content(json)
				                 .with(csrf())
				                 .secure(false)
				        ).andDo(print())
		                 .andExpect(status().isCreated());
	}
}
