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
			throw new RegraNegocioException("Lançamento Duplicado.");
		}
		this.repository.salvar(lancamento);
	}
	
	private boolean existeLancamentoDuplicado(Lancamento lancamento) {
		Lancamento lancamentoDuplicado = this.repository.verificarLancamentoIgual(lancamento);
		return lancamentoDuplicado != null && !lancamentoDuplicado.equals(lancamento);
	}

	/**
	 * Exclui o Lançamento selecionado
	 * @param lancamento Lancamento selecionado pelo usuário
	 * @throws RegraNegocioException Lançamento pago não pode ser excluído
	 */
	public void excluir(Lancamento lancamento) throws RegraNegocioException{
		
		if(lancamento.isPago()) {
			throw new RegraNegocioException("Lançamento PAGO não pode ser excluído.");
		}
		else {
		
			this.repository.excluir(lancamento);
		}
	}
}
