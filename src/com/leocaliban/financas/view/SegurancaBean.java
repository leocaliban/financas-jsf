package com.leocaliban.financas.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.leocaliban.financas.util.FacesUtil;

@ManagedBean
public class SegurancaBean {

	private String usuario;
	private String senha;
	
	/**
	 * Logar no sistema
	 * @return página de destino após login
	 */
	public String login() {
		try {
			this.getRequest().login(this.usuario, this.senha);
			//login correto
			return "Home?faces-redirect=true";
		} 
		catch (ServletException e) {
			FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Login incorreto.");
			//retorna null para permanecer na mesma tela
			return null;
		}
	}
	/**
	 * Logout do sistema
	 * @throws ServletException 
	 */
	public String logout() throws ServletException {
		this.getRequest().logout();
		return "Login?faces-redirect=true";		
	}
	
	/**
	 * Obter o request no context
	 * @return Request
	 */
	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest)context.getExternalContext().getRequest();
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
