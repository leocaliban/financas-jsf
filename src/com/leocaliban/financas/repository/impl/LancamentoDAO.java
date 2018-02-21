package com.leocaliban.financas.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
	public Lancamento buscarPorCodigo(Integer codigo) {
		return (Lancamento)this.session.get(Lancamento.class, codigo);
	}

	@Override
	public Lancamento salvar(Lancamento lancamento) {
		return (Lancamento)session.merge(lancamento);
	}

	@Override
	public void excluir(Lancamento lancamento) {
		this.session.delete(lancamento);		
	}

	@Override
	public Lancamento verificarLancamentoIgual(Lancamento lancamento) {
		return (Lancamento)this.session.createCriteria(Lancamento.class)
				.add(Restrictions.eq("tipo", lancamento.getTipo()))
				.add(Restrictions.eq("pessoa", lancamento.getPessoa()))
				//ilike compara sem case sensitive
				.add(Restrictions.ilike("descricao", lancamento.getDescricao()))
				.add(Restrictions.eq("valor", lancamento.getValor()))
				.add(Restrictions.eq("dataVencimento", lancamento.getDataVencimento()))
				.uniqueResult();
	}
}
