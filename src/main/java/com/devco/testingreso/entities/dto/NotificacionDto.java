package com.devco.testingreso.entities.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Builder @ToString
public class NotificacionDto {

	public static final String ETAPA_1 = "ETAPA 1";
	public static final String ETAPA_2 = "ETAPA 2";
	public static final String ETAPA_3 = "ETAPA 3";
	public static final String PENDIETE = "PENDIETE";
	public static final String ENVIADO = "ENVIADO";
	public static final String ERROR_ENVIO = "ERROR EN ENVIO";

	public static final String MSN_ETAPA_1 = "FELICITACIONES HA PASADO A LA ETAPA 2 DE NUESTRO PROCESO.";
	public static final String MSN_ETAPA_2 = "FELICITACIONES HA PASADO A LA ETAPA FINAL DE NUESTRO PROCESO.";
	public static final String MSN_ETAPA_3 = "FIN DEL PROCESO CON DEVCO MUCHAS GRACIAS POR PARTICIPAR";
	
	private String etapa;
	private Long idEtapa;
	private String mail;
	private String estado;

	public String getMensaje() {
		if(etapa.equals(ETAPA_1)) {
			return MSN_ETAPA_1;
		}
		else if(etapa.equals(ETAPA_2)) {
			return MSN_ETAPA_2;
		}
		return MSN_ETAPA_3;
	}
}
