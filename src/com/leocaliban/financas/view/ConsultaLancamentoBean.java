package com.leocaliban.financas.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.leocaliban.financas.model.Lancamento;
import com.leocaliban.financas.util.HibernateUtil;

@ManagedBean
public class ConsultaLancamentoBean {
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void inicializar() {
		Session session = HibernateUtil.getSession();
		
		this.lancamentos = session.createCriteria(Lancamento.class).addOrder(Order.desc("dataVencimento")).list();
		
		session.close();
		
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

}