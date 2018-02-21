package com.leocaliban.financas.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.repository.LancamentoRepository;

public class LancamentoDAO implements LancamentoRepository {

private Session session;
	
	public LancamentoDAO(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> buscarTodos() {
		return session.createCriteria(Lancamento.class).addOrder(Order.desc("dataVencimento")).list();
	}

	@Override
	public Lancamento salvar(Lancamento lancamento) {
		return (Lancamento)session.merge(lancamento);
	}

	@Override
	public void excluir(Lancamento lancamento) {
		this.session.delete(lancamento);		
	}

}
