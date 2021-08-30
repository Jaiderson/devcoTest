package com.devco.testingreso.utils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.devco.testingreso.utils.mails.EnviarMail;
import com.google.common.collect.Lists;

public class Util {

	private Util() {
		super();
	}

	/**
	 * Metodo que permite validar el formato de una fecha.
	 * 
	 * @param fecha Fecha a comprobar formato (yyyy-mm-dd)
	 * @return Dato con el contenido del resultado de la validacion.
	 */
	public static Dato stringToDate(String fecha) {
		Dato dato = new Dato();
		try {
			LocalDate localDate = LocalDate.parse(fecha);
			ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
			dato.setValor(Date.from(zonedDateTime.toInstant()));
			dato.setOk(true);
		} catch (Exception e) {
			dato.setValor(null);
			dato.setValor(false);
		}
		return dato;
	}

	public static void enviarMail(String cuerpoMailString, List<String> mailsReceptores) throws AddressException, MessagingException, IOException {
		EnviarMail sendMail = new EnviarMail("smtp.gmail.com", 587, "jaider.serrano.2021@gmail.com", "Medellin123+-*");
		sendMail.sendEmail(mailsReceptores, "Asunto - Prueba", cuerpoMailString, Lists.newArrayList());
	}

	public static String getFecha(Date fecha){
		if(fecha == null){
			return "";
		}
		
		LocalDateTime fec = LocalDateTime.ofInstant(new Date(fecha.getTime()).toInstant(), ZoneId.systemDefault());
		return fec.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
	
	public static Date getFecha(LocalDateTime fecha){
		if(fecha == null){
			return null;
		}
		
	    Instant instant = fecha.atZone(ZoneId.systemDefault()).toInstant();
	    return Date.from(instant);		
	}

}
