package com.projetoweb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
	
	private int id;
	private Date data;
	private List<ItemVenda> itens = new ArrayList<>();
	private Double valorTotal;
	private Cliente cliente; 
	
	public Venda() {
        this.data = new Date();
        this.valorTotal = calcularValorTotal();
    }

    public Venda(int id, Date data, List<ItemVenda> itens) {
        this.id = id;
        this.data = data;
        this.itens = itens;
        this.valorTotal = calcularValorTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
        this.valorTotal = calcularValorTotal();
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    private Double calcularValorTotal() {
        double total = 0.0;
        for (ItemVenda item : itens) {
            total += item.getValorTotal();
        }
        return total;
    } 
    

    public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
        this.valorTotal = calcularValorTotal();
    }

    public void removerItem(ItemVenda item) {
        this.itens.remove(item);
        this.valorTotal = calcularValorTotal();
    }

}
