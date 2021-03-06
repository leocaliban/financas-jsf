package com.leocaliban.financas.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sun.faces.util.MessageFactory;

@FacesValidator("com.leocaliban.DataFutura")
public class DataFuturaValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Date data = (Date) value;
		if(data !=null && data.after(new Date())) {
			//busca o label na p�gina como um objeto
			Object label = MessageFactory.getLabel(context, component);
			//cria a mensagem
			String descricaoErro = label + " N�o pode ser uma data futura.";
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ValidatorException(message);
		}	
	}
}
