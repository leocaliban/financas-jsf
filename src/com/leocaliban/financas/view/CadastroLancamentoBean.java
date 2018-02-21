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

import org.hibernate.Session;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.model.Pessoa;
import com.leocaliban.financas.model.TipoLancamento;
import com.leocaliban.financas.repository.PessoaRepository;
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
	
	public void cadastrar() {
		Session session = (Session)FacesUtil.getRequestAttribute("session");
		session.merge(this.lancamento);
		
		this.lancamento = new Lancamento();
		
		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
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

}
