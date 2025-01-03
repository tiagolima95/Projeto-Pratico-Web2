package com.projetoweb.controllers;

import com.projetoweb.dto.CategoriaDTO;
import com.projetoweb.dto.ProdutoDTO;
import com.projetoweb.model.Categoria;
import com.projetoweb.model.Produto;
import com.projetoweb.utils.ConnectionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int pagina = request.getParameter("pagina") != null ? Integer.parseInt(request.getParameter("pagina")) : 1;
	    int itensPorPagina = 5;
	    
	    String idParam = request.getParameter("id");
	    
	    try (Connection conexao = ConnectionFactory.getConnection()) {
	        ProdutoDTO produtoDTO = new ProdutoDTO(conexao);

	        if (idParam != null) { 
	            int id = Integer.parseInt(idParam);
	            Produto produto = produtoDTO.findById(id);

	            if (produto != null) {
	                CategoriaDTO categoriaDTO = new CategoriaDTO(conexao);
	                List<Categoria> categorias = categoriaDTO.list();
	                request.setAttribute("produto", produto);
	                request.setAttribute("categorias", categorias);
	                request.getRequestDispatcher("/WEB-INF/views/editarProduto.jsp").forward(request, response);
	                return;
	            }
	        }
	        
	        List<Produto> produtos = produtoDTO.listPaginado(pagina, itensPorPagina);
	        int totalProdutos = produtoDTO.count();
	        int totalPaginas = (int) Math.ceil((double) totalProdutos / itensPorPagina);

	        CategoriaDTO categoriaDTO = new CategoriaDTO(conexao);
	        List<Categoria> categorias = categoriaDTO.list();

	        request.setAttribute("produtos", produtos);
	        request.setAttribute("paginaAtual", pagina);
	        request.setAttribute("totalPaginas", totalPaginas);
	        request.setAttribute("categorias", categorias);
	        request.getRequestDispatcher("/WEB-INF/views/produto.jsp").forward(request, response);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new ServletException("Erro ao conectar ao banco de dados", e);
	    }
	}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action"); 
	    String idParam = request.getParameter("id");

	    try (Connection conexao = ConnectionFactory.getConnection()) {
	        ProdutoDTO produtoDTO = new ProdutoDTO(conexao);
	        
	        if ("excluir".equals(action)) {
	            if (idParam != null && !idParam.isEmpty()) {
	                int produtoId = Integer.parseInt(idParam);
	                produtoDTO.delete(produtoId); 
	            }
	        } else {
	            String nome = request.getParameter("nome");
	            String descricao = request.getParameter("descricao");
	            int quantidadeEstoque = Integer.parseInt(request.getParameter("quantidade_estoque"));
	            double preco = Double.parseDouble(request.getParameter("preco"));
	            int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));

	            Produto produto = new Produto();
	            produto.setNome(nome);
	            produto.setDescricao(descricao);
	            produto.setQuantidadeEstoque(quantidadeEstoque);
	            produto.setPreco(preco);

	            Categoria categoria = new Categoria();
	            categoria.setId(categoriaId);
	            produto.setCategoria(categoria);

	            if (idParam != null && !idParam.isEmpty()) {
	                produto.setId(Integer.parseInt(idParam));
	                produtoDTO.update(produto); 
	            } else {
	                produtoDTO.save(produto);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new ServletException("Erro ao salvar ou excluir produto", e);
	    }

	    response.sendRedirect(request.getContextPath() + "/produtos");
	}
}
