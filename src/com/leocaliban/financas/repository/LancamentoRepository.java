package com.leocaliban.financas.repository;

import java.util.List;

import com.leocaliban.financas.model.Lancamento;

public interface LancamentoRepository {
	
	public List<Lancamento> buscarTodos();
		
	public Lancamento salvar(Lancamento lancamento);
	
	public void excluir(Lancamento lancamento);
}
