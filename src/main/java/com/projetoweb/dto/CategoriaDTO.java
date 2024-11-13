package com.projetoweb.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projetoweb.model.Categoria;
import com.projetoweb.model.Cliente;

public class CategoriaDTO implements IDTO<Categoria> {
	
	private Connection conexao;
	 
	 public CategoriaDTO(Connection conexao) {
	        this.conexao = conexao;
	    }

	@Override
	public void save(Categoria categoria) {
		String sql = "INSERT INTO categoria (nome) VALUES (?)";
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
		     stmt.setString(1, categoria.getNome());
		     stmt.executeUpdate();
		} catch (SQLException e) {
		     e.printStackTrace();
		    }
		
	}

	@Override
	public List<Categoria> list() {
		List<Categoria> categorias = new ArrayList<Categoria>();		
		String sql = "SELECT * FROM categoria";
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                Categoria categoria = new Categoria();
	                categoria.setId(rs.getInt("id"));
	                categoria.setNome(rs.getString("nome"));

	                categorias.add(categoria);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return categorias;
	}

	@Override
	public void update(Categoria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
