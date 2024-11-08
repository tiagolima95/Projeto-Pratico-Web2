package com.projetoweb.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
	
	private int id;
	private String nome;
	private List<Produto> produtos;
	
	public Categoria(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
		this.produtos = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	
	

}
