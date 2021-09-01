package com.devco.testingreso.servicetests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devco.testingreso.entities.Candidato;
import com.devco.testingreso.entities.Rol;
import com.devco.testingreso.repositories.ICandidatoRep;
import com.devco.testingreso.services.ICandidatoService;
import com.devco.testingreso.services.implementations.CandidatoServiceImpl;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CandidatoServiceTest {

	@Autowired
	private ICandidatoRep candidatoRep;
	
	private Candidato candidato;
	
	private ICandidatoService candidatoService;

	@BeforeEach
	public void init() {
		candidatoService = new CandidatoServiceImpl(candidatoRep);
		
		this.candidato = Candidato.builder()
			                      .nombre("JAIDER SERRANO VILLASZON")
			                      .dni("C28123456")
			                      .email("jaider.tns@gmail.com")
			                      .rol(Rol.builder().idRol(1L).build())
			                      .build();
	}

	@Test
	void buscarCandidatoTest() {
		candidato = candidatoService.crearCandidato(candidato);
		List<Candidato> candidatos = candidatoRep.findAll();
		assertThat(candidatos).isNotNull();
		assertThat(candidatos.size()).isPositive();
	}

	@Test
	void crearCandidatoTest() {
		candidato = candidatoService.crearCandidato(candidato);
		assertThat(candidato).isNotNull();
		assertThat(candidato.getIdCandidato()).isPositive();
	}

	@Test
	void modificarCandidatoTest() {
		candidato = candidatoService.crearCandidato(candidato);
		assertThat(candidato).isNotNull();
		assertThat(candidato.getIdCandidato()).isPositive();

		candidato.setNombre("Jaider Jesus Serrano Villason");
		candidato = candidatoService.modificarCandidato(candidato);
		assertThat(candidato).isNotNull();
		assertThat(candidato.getNombre()).isEqualTo("Jaider Jesus Serrano Villason");
	}

	@Test
	void eliminarCandidatoTest() {
		candidato = candidatoService.crearCandidato(candidato);
		assertThat(candidato).isNotNull();
		assertThat(candidato.getIdCandidato()).isPositive();

		candidatoService.eliminarCandidato(candidato.getIdCandidato());
		candidato = candidatoService.buscarCandidatoPorId(candidato.getIdCandidato());
		assertThat(candidato).isNull();
	}

}
