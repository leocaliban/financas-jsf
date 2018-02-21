package com.leocaliban.financas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.leocaliban.financas.model.Pessoa;
import com.leocaliban.financas.repository.PessoaRepository;
import com.leocaliban.financas.util.Repositorios;

//aponta o conversosr para a classe Pessoa
@FacesConverter(forClass=Pessoa.class)
public class PessoaConverter implements Converter{
	
	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa pessoaRetorno = null;
		
		if(value != null) {
			PessoaRepository repository = this.repositorios.getPessoas();
			
			pessoaRetorno = repository.buscarPorCodigo(new Integer(value));
		}
		return pessoaRetorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
		return ((Pessoa)value).getCodigo().toString();
		}
		return null;
	}
}
