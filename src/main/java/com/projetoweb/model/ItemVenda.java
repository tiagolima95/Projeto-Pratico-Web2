package com.projetoweb.model;

public class ItemVenda {
	private Produto produto;
	private int quantidade;
	private Double precoUnitario;
	private Double valorTotal;
	
	public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();  
        this.valorTotal = calcularValorTotal(); 
    }
	
	private Double calcularValorTotal() {
        return precoUnitario * quantidade;
    }
	
	public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.precoUnitario = produto.getPreco();
        this.valorTotal = calcularValorTotal();  
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.valorTotal = calcularValorTotal();  
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
        this.valorTotal = calcularValorTotal();  
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

