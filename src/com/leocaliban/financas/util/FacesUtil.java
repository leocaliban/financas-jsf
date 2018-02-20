package com.leocaliban.financas.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static void adicionarMensagem(Severity tipo, String mensagem) {
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new	FacesMessage(tipo, mensagem, mensagem));
	}
}
