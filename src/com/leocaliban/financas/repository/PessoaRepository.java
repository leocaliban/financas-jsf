package com.leocaliban.financas.repository;

import java.util.List;

import com.leocaliban.financas.model.Pessoa;

public interface PessoaRepository {

	/**
	 * Recupera todas as pessoas do banco
	 * @return Lista de Pessoas
	 */
	public List<Pessoa> buscarTodos();
	
	/**
	 * Busca uma pessoa pelo seu c�digo
	 * @param codigo c�digo id da Pessoa
	 * @return uma Pessoa
	 */
	public Pessoa buscarPorCodigo(Integer codigo);
	
}