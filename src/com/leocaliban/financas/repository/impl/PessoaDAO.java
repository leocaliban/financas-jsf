package com.leocaliban.financas.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.leocaliban.financas.model.Pessoa;
import com.leocaliban.financas.repository.PessoaRepository;

public class PessoaDAO implements PessoaRepository{

	private Session session;
	
	public PessoaDAO(Session session) {
		this.session = session;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> buscarTodos() {
		return session.createCriteria(Pessoa.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Pessoa buscarPorCodigo(Integer codigo) {
		return (Pessoa) session.get(Pessoa.class, new Integer(codigo));
	}
}
