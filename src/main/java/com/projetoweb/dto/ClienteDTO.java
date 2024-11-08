package com.projetoweb.dto;

import com.projetoweb.model.Cliente;
import com.projetoweb.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ClienteDTO implements IDTO<Cliente> {
	
	 private Connection conexao;
	 
	 public ClienteDTO(Connection conexao) {
	        this.conexao = conexao;
	    }

	@Override
	public List<Cliente> list(){
		 List<Cliente> clientes = new ArrayList<>();
	        String sql = "SELECT * FROM cliente";

	        try (PreparedStatement stmt = conexao.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Cliente cliente = new Cliente();
	                cliente.setId(rs.getInt("id"));
	                cliente.setNome(rs.getString("nome"));
	                cliente.setEmail(rs.getString("email"));

	                clientes.add(cliente);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return clientes;		
	}

	@Override
	public void update(Cliente cliente) {
	    String sql = "UPDATE cliente SET nome = ?, email = ? WHERE id = ?";

	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, cliente.getNome());
	        stmt.setString(2, cliente.getEmail());
	        stmt.setInt(3, cliente.getId());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
	}
}