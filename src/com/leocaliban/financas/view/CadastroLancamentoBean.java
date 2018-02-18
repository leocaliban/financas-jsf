package com.leocaliban.financas.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.model.TipoLancamento;

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Lancamento lancamento = new Lancamento();
	
	public void cadastrar() {
		System.out.println("Tipo: " + this.lancamento.getTipo());
		//System.out.println("Pessoa: " + this.lancamento.getPessoa().getNome());
		System.out.println("Descri��o: " + this.lancamento.getDescricao());
		System.out.println("Valor: " + this.lancamento.getValor());
		System.out.println("Data de Vencimento: " + this.lancamento.getDataVencimento());
		System.out.println("Conta paga: " + this.lancamento.isPago());
		System.out.println("Data pagamento: " + this.lancamento.getDataPagamento());
		
		this.lancamento = new Lancamento();
	}
	
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}
	
}
