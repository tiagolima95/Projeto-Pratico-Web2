package com.projetoweb.controllers;

import com.projetoweb.dto.ClienteDTO;
import com.projetoweb.model.Cliente;
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

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conexao = ConnectionFactory.getConnection()) {
            ClienteDTO clienteDTO = new ClienteDTO(conexao);

            List<Cliente> clientes = clienteDTO.list();

            request.setAttribute("clientes", clientes);

            request.getRequestDispatcher("/WEB-INF/views/cliente.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao conectar ao banco de dados", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        try (Connection conexao = ConnectionFactory.getConnection()) {
            ClienteDTO clienteDTO = new ClienteDTO(conexao);
            clienteDTO.save(cliente); 
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao salvar cliente", e);
        }

        response.sendRedirect(request.getContextPath() + "/clientes");
    }
}
