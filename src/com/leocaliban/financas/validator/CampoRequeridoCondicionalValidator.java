package com.leocaliban.financas.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sun.faces.util.MessageFactory;

@FacesValidator("com.leocaliban.CampoRequeridoCondicional")
public class CampoRequeridoCondicionalValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		//buscando o atributo criado, que retorna um objeto booleano
		Boolean checado = (Boolean) component.getAttributes().get("checado");
		
		if(checado != null && checado && value == null) {
			Object label = MessageFactory.getLabel(context, component);
			String descricaoErro = "Preencha o campo " + label + ".";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ValidatorException(message);
		}
		
	}

}
