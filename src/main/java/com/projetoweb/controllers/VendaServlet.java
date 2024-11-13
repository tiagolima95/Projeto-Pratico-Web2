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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paginaAtual = request.getParameter("pagina") != null ? Integer.parseInt(request.getParameter("pagina")) : 1;
        int itensPorPagina = 10;

        try (Connection conexao = ConnectionFactory.getConnection()) {
            ProdutoDTO produtoDTO = new ProdutoDTO(conexao);
            List<Produto> produtos = produtoDTO.listPaginado(paginaAtual, itensPorPagina); // Sem filtro de nome
            int totalProdutos = produtoDTO.count(); // Sem filtro de nome
            int totalPaginas = (int) Math.ceil((double) totalProdutos / itensPorPagina);

            // Definindo os atributos para a JSP
            request.setAttribute("produtos", produtos);
            request.setAttribute("totalPaginas", totalPaginas);
            
            System.out.println("Produtos: " + produtos);

            request.getRequestDispatcher("/WEB-INF/views/venda.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp"); // Página de erro
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conexao = ConnectionFactory.getConnection()) {
            // Obter o cliente
            int clienteId = Integer.parseInt(request.getParameter("clienteId"));
            ClienteDTO clienteDTO = new ClienteDTO(conexao);
            Cliente cliente = clienteDTO.findById(clienteId);

            if (cliente == null) {
                response.sendRedirect("clienteNaoEncontrado.jsp");
                return;
            }

            // Recuperar os produtos novamente, pois precisamos das informações para verificar estoque
            ProdutoDTO produtoDTO = new ProdutoDTO(conexao);
            List<Produto> produtos = produtoDTO.list(); // Obtenha todos os produtos para o processamento

            // Criar a lista de itens para a venda
            List<ItemVenda> itensVenda = new ArrayList<>();

            // Loop para processar cada produto enviado no formulário
            for (Produto produto : produtos) {
                String quantidadeStr = request.getParameter("quantidade_" + produto.getId());
                if (quantidadeStr != null) {
                    int quantidade = Integer.parseInt(quantidadeStr);

                    // Verificar se a quantidade disponível é suficiente
                    if (produto.getQuantidadeEstoque() >= quantidade) {
                        // Criar o item de venda e adicionar à lista de itens
                        ItemVenda itemVenda = new ItemVenda(produto, quantidade);
                        itensVenda.add(itemVenda);

                        // Atualizar o estoque do produto
                        int novoEstoque = produto.getQuantidadeEstoque() - quantidade;
                        produto.setQuantidadeEstoque(novoEstoque);  // Atualiza a quantidade do produto

                        // Atualizar o estoque no banco de dados
                        produtoDTO.updateEstoque(produto);  // Chama o método para atualizar o estoque no banco de dados
                    } else {
                        // Caso o estoque seja insuficiente, redireciona para erro
                        response.sendRedirect("estoqueInsuficiente.jsp");
                        return;
                    }
                }
            }

            // Calcular o valor total da venda
            double valorTotal = 0;
            for (ItemVenda item : itensVenda) {
                valorTotal += item.getValorTotal(); // Supondo que o ItemVenda já calcule o valor total com base na quantidade
            }

            // Criar a venda e associar o cliente e os itens
            Venda venda = new Venda();
            venda.setCliente(cliente);
            venda.setData(new Date());
            venda.setItens(itensVenda);
            venda.setValorTotal(valorTotal);

            // Salvar a venda no banco de dados
            VendaDTO vendaDTO = new VendaDTO(conexao);
            vendaDTO.save(venda);

            // Redirecionar para a página de sucesso
            response.sendRedirect("vendaSucesso.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}