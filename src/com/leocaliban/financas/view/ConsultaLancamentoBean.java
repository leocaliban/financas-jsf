package com.leocaliban.financas.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.repository.LancamentoRepository;
import com.leocaliban.financas.util.FacesUtil;
import com.leocaliban.financas.util.Repositorios;

@ManagedBean
public class ConsultaLancamentoBean {
	
	private Repositorios repositorios = new Repositorios();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private Lancamento lancamentoSelecionado;
	
	@PostConstruct
	public void inicializar() {
		LancamentoRepository repository = this.repositorios.getLancamentos();
		this.lancamentos = repository.buscarTodos();
	}
	
	public void excluir() {
		if(this.lancamentoSelecionado.isPago()) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Lan�amento PAGO n�o pode ser exclu�do.");
		}
		else {
			LancamentoRepository repository = this.repositorios.getLancamentos();
			repository.excluir(this.lancamentoSelecionado);

			//recarregar a lista ap�s a exclus�o
			this.inicializar();
			
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Lan�amento Exclu�do com sucesso.");
		}
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}
	
}