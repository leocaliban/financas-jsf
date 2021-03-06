package com.leocaliban.financas.service;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.repository.LancamentoRepository;
import com.leocaliban.financas.service.exceptions.RegraNegocioException;


public class LancamentoService {
	private LancamentoRepository repository;
	
	public LancamentoService(LancamentoRepository repository) {
		this.repository = repository;
	}
	
	public void salvar(Lancamento lancamento) throws RegraNegocioException {
		
		if(existeLancamentoDuplicado(lancamento)) {
			throw new RegraNegocioException("Lan�amento Duplicado.");
		}
		this.repository.salvar(lancamento);
	}
	
	private boolean existeLancamentoDuplicado(Lancamento lancamento) {
		Lancamento lancamentoDuplicado = this.repository.verificarLancamentoIgual(lancamento);
		return lancamentoDuplicado != null && !lancamentoDuplicado.equals(lancamento);
	}

	/**
	 * Exclui o Lan�amento selecionado
	 * @param lancamento Lancamento selecionado pelo usu�rio
	 * @throws RegraNegocioException Lan�amento pago n�o pode ser exclu�do
	 */
	public void excluir(Lancamento lancamento) throws RegraNegocioException{
		
		if(lancamento.isPago()) {
			throw new RegraNegocioException("Lan�amento PAGO n�o pode ser exclu�do.");
		}
		else {
		
			this.repository.excluir(lancamento);
		}
	}
}
