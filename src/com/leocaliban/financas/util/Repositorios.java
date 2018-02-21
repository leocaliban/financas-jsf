package com.leocaliban.financas.util;

import java.io.Serializable;

import org.hibernate.Session;

import com.leocaliban.financas.repository.PessoaRepository;
import com.leocaliban.financas.repository.impl.PessoaDAO;

public class Repositorios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PessoaRepository getPessoas() {
		return new PessoaDAO(this.getSession());
	}
	
	private Session getSession() {
		return (Session)FacesUtil.getRequestAttribute("session");
	}
}
