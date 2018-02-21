package com.leocaliban.financas.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.repository.LancamentoRepository;
import com.leocaliban.financas.util.Repositorios;

@FacesConverter(forClass=Lancamento.class)
public class LancamentoConverter implements Converter{
	
	private Repositorios repositoriosBean = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value){
		Lancamento retorno = null;
		LancamentoRepository repository = this.repositoriosBean.getLancamentos();
		
		if(value != null && !value.equals("")) {
			retorno = repository.buscarPorCodigo(new Integer(value));
			
			if(retorno == null) {
				String descricaoErro = "Lançamento Não Existe";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
				throw new ConverterException(message);	
			}
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value){
		if(value != null) {
			Integer codigo = ((Lancamento) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}
