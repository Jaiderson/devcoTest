package com.devco.testingreso.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

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

}
