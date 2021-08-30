package com.devco.testingreso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devco.testingreso.entities.EtapaFinal;
import com.devco.testingreso.entities.EtapaInicial;
import com.devco.testingreso.entities.EtapaIntermedia;
import com.devco.testingreso.entities.dto.NotificacionDto;
import com.devco.testingreso.services.IEtapaFinalService;
import com.devco.testingreso.services.IEtapaInicialService;
import com.devco.testingreso.services.IEtapaIntermediaService;
import com.google.common.collect.Lists;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.devco.testingreso.utils.Util;

@Controller
@RequestMapping(value = "/notificar")
public class NotificadorController {

	@Autowired
	private IEtapaInicialService etapaInicialService;
	@Autowired
	private IEtapaIntermediaService etapaIntermediaService;
	@Autowired
	private IEtapaFinalService etapaFinalService;

	@GetMapping
	@ApiOperation(value = "Proceso de notificacion que busca en cada etapa los registros pendientes por notificar. (mailEnviado = 'NO') "
			            + "Cuando el proceso envia las notificaciones marca lso registros como enviados.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Lista de notificaciones enviadas."),
		@ApiResponse(code = 401, message = "Acceso al recurso no autorizado.")
	})
	public ResponseEntity<List<NotificacionDto>> notificar(){
		List<NotificacionDto> notificaciones = Lists.newArrayList();
		notificaciones.addAll(listaEtapasInicialesNotificar());
		notificaciones.addAll(listaEtapasIntermediasNotificar());
		notificaciones.addAll(listaEtapasFinalesNotificar());

		notificar(notificaciones);
		notificaciones = actualizarEnviados(notificaciones);
		return ResponseEntity.ok(notificaciones);
	}

	private List<NotificacionDto> listaEtapasInicialesNotificar(){
		List<NotificacionDto> notificaciones = Lists.newArrayList();
		List<EtapaInicial> etapasIniciales = etapaInicialService.etapasInicialesSinNotificar();

		if(!etapasIniciales.isEmpty()) {
			etapasIniciales.stream().forEach((etapa1) -> {
				NotificacionDto notificacion = NotificacionDto.builder()
						.etapa(NotificacionDto.ETAPA_1)
						.idEtapa(etapa1.getIdEtapa())
						.estado(NotificacionDto.PENDIETE)
						.mail(etapa1.getCandidato().getEmail())
						.build();
				notificaciones.add(notificacion);
			});
		}
		return notificaciones;
	}

	private List<NotificacionDto> listaEtapasIntermediasNotificar(){
		List<NotificacionDto> notificaciones = Lists.newArrayList();
		List<EtapaIntermedia> etapasIntermedias = etapaIntermediaService.etapasIntermediaSinNotificar();

		if(!etapasIntermedias.isEmpty()) {
			etapasIntermedias.stream().forEach((etapa2) -> {
				NotificacionDto notificacion = NotificacionDto.builder()
						.etapa(NotificacionDto.ETAPA_2)
						.idEtapa(etapa2.getIdEtapaM())
						.estado(NotificacionDto.PENDIETE)
						.mail(etapa2.getEtapaInicial().getCandidato().getEmail())
						.build();
				notificaciones.add(notificacion);
			});
		}
		return notificaciones;
	}

	private List<NotificacionDto> listaEtapasFinalesNotificar(){
		List<NotificacionDto> notificaciones = Lists.newArrayList();
		List<EtapaFinal> etapasIniciales = etapaFinalService.etapasFinalesSinNotificar();

		if(!etapasIniciales.isEmpty()) {
			etapasIniciales.stream().forEach((etapa3) -> {
				NotificacionDto notificacion = NotificacionDto.builder()
						.etapa(NotificacionDto.ETAPA_3)
						.idEtapa(etapa3.getIdEtapaF())
						.estado(NotificacionDto.PENDIETE)
						.mail(etapa3.getEtapaIntermedia().getEtapaInicial().getCandidato().getEmail())
						.build();
				notificaciones.add(notificacion);
			});
		}
		return notificaciones;
	}

	private void notificar(List<NotificacionDto> notificaciones) {
		if(!notificaciones.isEmpty()) {
			notificaciones.stream().forEach((notificacion -> {
				try {
					List<String> mails = Lists.newArrayList();
					mails.add(notificacion.getMail());
					Util.enviarMail(notificacion.getMensaje(), mails);
					notificacion.setEstado(NotificacionDto.ENVIADO);
				}
			    catch(Exception e) {
			    	notificacion.setEstado(NotificacionDto.ERROR_ENVIO);
			    }
			}));
		}
	}

	private List<NotificacionDto> actualizarEnviados(List<NotificacionDto> notificaciones) {
		List<NotificacionDto> result = Lists.newArrayList();

		if(!notificaciones.isEmpty()) {
			notificaciones.stream().filter((notificacion) -> notificacion.getEstado().equals(NotificacionDto.ENVIADO))
			                       .forEach((noti) -> {
			                    	   //TODO Hacer update para marcar registro como enviado.
			                    	   System.out.println(noti.toString());
			                    	   result.add(noti);
			                       });
		}
		return result;
	}

}
