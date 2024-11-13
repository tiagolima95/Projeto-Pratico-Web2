package com.projetoweb.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.projetoweb.dto.ClienteDTO;
import com.projetoweb.dto.ProdutoDTO;
import com.projetoweb.dto.VendaDTO;
import com.projetoweb.model.Cliente;
import com.projetoweb.model.ItemVenda;
import com.projetoweb.model.Produto;
import com.projetoweb.model.Venda;
import com.projetoweb.utils.ConnectionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/realizarVenda")
public class VendaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int paginaAtual = request.getParameter("pagina") != null ? Integer.parseInt(request.getParameter("pagina")) : 1;
		int itensPorPagina = 10;

		try (Connection conexao = ConnectionFactory.getConnection()) {
			ProdutoDTO produtoDTO = new ProdutoDTO(conexao);
			List<Produto> produtos = produtoDTO.listPaginado(paginaAtual, itensPorPagina);
			int totalProdutos = produtoDTO.count();
			int totalPaginas = (int) Math.ceil((double) totalProdutos / itensPorPagina);

			request.setAttribute("produtos", produtos);
			request.setAttribute("totalPaginas", totalPaginas);

			System.out.println("Produtos: " + produtos);

			request.getRequestDispatcher("/WEB-INF/views/venda.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("erro.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try (Connection conexao = ConnectionFactory.getConnection()) {
	        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
	        ClienteDTO clienteDTO = new ClienteDTO(conexao);
	        Cliente cliente = clienteDTO.findById(clienteId);

	        if (cliente == null) {
	            response.sendRedirect("clienteNaoEncontrado.jsp");
	            return;
	        }

	        ProdutoDTO produtoDTO = new ProdutoDTO(conexao);
	        List<Produto> produtos = produtoDTO.list();

	        List<ItemVenda> itensVenda = new ArrayList<>();
	        boolean estoqueSuficiente = true;

	        for (Produto produto : produtos) {
	            String quantidadeStr = request.getParameter("quantidade_" + produto.getId());
	            if (quantidadeStr != null) {
	                int quantidade = Integer.parseInt(quantidadeStr);

	                if (produto.getQuantidadeEstoque() >= quantidade) {
	                    ItemVenda itemVenda = new ItemVenda(produto, quantidade);
	                    itensVenda.add(itemVenda);

	                    int novoEstoque = produto.getQuantidadeEstoque() - quantidade;
	                    produto.setQuantidadeEstoque(novoEstoque);

	                    produtoDTO.updateEstoque(produto);
	                } else {
	                    estoqueSuficiente = false;
	                    break; 
	                }
	            }
	        }

	        if (!estoqueSuficiente) {
	            response.sendRedirect("estoqueInsuficiente.jsp");
	            return; 
	        }


	        double valorTotal = 0;
	        for (ItemVenda item : itensVenda) {
	            valorTotal += item.getValorTotal();
	        }

	        Venda venda = new Venda();
	        venda.setCliente(cliente);
	        venda.setData(new Date());
	        venda.setItens(itensVenda);
	        venda.setValorTotal(valorTotal);

	        VendaDTO vendaDTO = new VendaDTO(conexao);
	        vendaDTO.save(venda);

	        request.getRequestDispatcher("/WEB-INF/views/cliente.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("erro.jsp");
	    }
	}

}