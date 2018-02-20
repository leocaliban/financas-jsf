package com.leocaliban.financas.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.util.FacesUtil;

@ManagedBean
public class ConsultaLancamentoBean {
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private Lancamento lancamentoSelecionado;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void inicializar() {
		Session session = (Session)FacesUtil.getRequestAttribute("session");
		
		this.lancamentos = session.createCriteria(Lancamento.class).addOrder(Order.desc("dataVencimento")).list();
	}
	
	public void excluir() {
		if(this.lancamentoSelecionado.isPago()) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Lan�amento PAGO n�o pode ser exclu�do.");
		}
		else {
			Session session = (Session)FacesUtil.getRequestAttribute("session");
			session.delete(lancamentoSelecionado);

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