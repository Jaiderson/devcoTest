package com.devco.testingreso.utils.mails;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.google.common.collect.Lists;

public class MainTest {
	
	public static void main(String[] args) throws AddressException, MessagingException, IOException{
		
		List<String> lista = Lists.newArrayList();
		lista.add("jaider.tns@gmail.com");
		lista.add("glendyzulay@gmail.com");
		enviarMail(lista);
	}
	
	private static void enviarMail(List<String> mailsReceptores) throws AddressException, MessagingException, IOException {
		System.out.println("Inicio Prueba enviar mail");
		
		EnviarMail sendMail = new EnviarMail("smtp.gmail.com", 587, "jaider.serrano.2021@gmail.com", "Medellin123+-*");
		
		//Si no vas a enviar adjuntos envias null o unalista vacia.
		String cuerpoMailString = "<b>Tutulo<b/> <br><br> Mensaje principal";
		sendMail.sendEmail(mailsReceptores, "Asunto - Prueba", cuerpoMailString, Lists.newArrayList());
		
		//Si no vas a enviar adjuntos envias null o unalista vacia como se muestra en la siguiente linea.
		//sendMail.sendEmail(mailsReceptores, "Asunto - Prueba", "Cuerpo mensaje: mail con adjuntos Verion 3", null);
		
	}
	
	
	
	
	
}
