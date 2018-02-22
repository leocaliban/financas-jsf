package com.leocaliban.financas.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class FacesUtil {
	
	public static void adicionarMensagem(Severity tipo, String mensagem) {
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new	FacesMessage(tipo, mensagem, mensagem));
	}
	
	public static Object getRequestAttribute(String nome) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
		return request.getAttribute(nome);
	}
	
	public static String getMensagemI18n(String chave) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String msg = facesContext.getApplication().getResourceBundle(facesContext, "msg").getString(chave);
		return msg;
	}
}
