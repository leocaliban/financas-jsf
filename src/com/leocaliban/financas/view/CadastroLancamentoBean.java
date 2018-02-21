package com.leocaliban.financas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.model.Pessoa;
import com.leocaliban.financas.model.TipoLancamento;
import com.leocaliban.financas.repository.PessoaRepository;
import com.leocaliban.financas.service.LancamentoService;
import com.leocaliban.financas.service.exceptions.RegraNegocioException;
import com.leocaliban.financas.util.FacesUtil;
import com.leocaliban.financas.util.Repositorios;

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Repositorios repositorios = new Repositorios();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private Lancamento lancamento = new Lancamento();
	
	@PostConstruct
	public void init() {
		PessoaRepository repository = this.repositorios.getPessoas();
		this.pessoas = repository.buscarTodos();
	}
	
	//Quando o checkbox do pagamento for modificado, este método irá capturar o evento e aplicar a alteração
	public void lancamentoPagoModificado(ValueChangeEvent event) {
		this.lancamento.setPago((Boolean)event.getNewValue());
		this.lancamento.setDataPagamento(null);
		//Força o ciclo de vida para a última fase para renderizar a resposta sem validar nada.
		FacesContext.getCurrentInstance().renderResponse();
	}
	
	public void salvar() {
		LancamentoService service = new LancamentoService(this.repositorios.getLancamentos());
		try {
			service.salvar(lancamento);
			this.lancamento = new Lancamento();
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Lançamento Salvo com sucesso!");
		} 
		catch (RegraNegocioException e) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
	}
	
	public boolean isEditando() {
		return this.lancamento.getCodigo() != null;
	}
	
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public void setLancamento(Lancamento lancamento) throws CloneNotSupportedException {
		this.lancamento = lancamento;
		if(this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
		else {
			this.lancamento = (Lancamento)lancamento.clone();
		}
	}
}
