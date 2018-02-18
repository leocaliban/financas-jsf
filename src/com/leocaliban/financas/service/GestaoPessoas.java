package com.leocaliban.financas.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leocaliban.financas.model.Pessoa;


public class GestaoPessoas {
	private static Map<Integer, Pessoa> pessoas = new HashMap<Integer, Pessoa>();
	
	static {
		pessoas.put(1, new Pessoa(1, "Rafael Linhares"));
		pessoas.put(2, new Pessoa(2, "Jack Bauer"));
		pessoas.put(3, new Pessoa(3, "Nina Myers"));
	}
	
	public List<Pessoa> listarTodas(){
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.addAll(GestaoPessoas.pessoas.values());
		return pessoas;
	}
	
	public Pessoa bucarPorCodigo(Integer codigo) {
		return pessoas.get(codigo);
	}
}
