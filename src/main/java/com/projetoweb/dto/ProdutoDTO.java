package com.projetoweb.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projetoweb.model.Categoria;
import com.projetoweb.model.Cliente;
import com.projetoweb.model.Produto;

public class ProdutoDTO implements IDTO<Produto>{
	
	private Connection conexao;
	 
	 public ProdutoDTO(Connection conexao) {
	        this.conexao = conexao;
	    }

	@Override
	public void save(Produto produto) {
		String sql = "INSERT INTO Produto (nome, descricao, quantidade_estoque, preco, categoria_id) VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
		     stmt.setString(1, produto.getNome());
		     stmt.setString(2, produto.getDescricao());
		     stmt.setInt(3, produto.getQuantidadeEstoque());
		     stmt.setDouble(4, produto.getPreco());
		     stmt.setInt(5, produto.getCategoria().getId());
		     stmt.executeUpdate();
		} catch (SQLException e) {
		     e.printStackTrace();
		    }
		}
		

	@Override
	public List<Produto> list() {
		List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                produto.setPreco(rs.getDouble("preco"));
                
                int categoriaId = rs.getInt("categoria_id"); 

                Categoria categoria = new Categoria();
                String categoriaSql = "SELECT * FROM categoria WHERE id = ?";
                
                try (PreparedStatement categoriaStmt = conexao.prepareStatement(categoriaSql)) {
                    categoriaStmt.setInt(1, categoriaId);
                    
                    try (ResultSet categoriaRs = categoriaStmt.executeQuery()) {
                        if (categoriaRs.next()) {
                            categoria.setId(categoriaRs.getInt("id"));
                            categoria.setNome(categoriaRs.getString("nome"));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                produto.setCategoria(categoria);

                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;	
	}
	
	public Produto findById(int id) {
	    String sql = "SELECT * FROM produto WHERE id = ?";
	    Produto produto = null;

	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, id);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                produto = new Produto();
	                produto.setId(rs.getInt("id"));
	                produto.setNome(rs.getString("nome"));
	                produto.setDescricao(rs.getString("descricao"));
	                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
	                produto.setPreco(rs.getDouble("preco"));

	                // Carregar a categoria associada
	                int categoriaId = rs.getInt("categoria_id");
	                Categoria categoria = findCategoriaById(categoriaId);
	                produto.setCategoria(categoria);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return produto;
	}

	// Método auxiliar para buscar a categoria pelo ID
	private Categoria findCategoriaById(int categoriaId) {
	    String categoriaSql = "SELECT * FROM categoria WHERE id = ?";
	    Categoria categoria = null;

	    try (PreparedStatement categoriaStmt = conexao.prepareStatement(categoriaSql)) {
	        categoriaStmt.setInt(1, categoriaId);

	        try (ResultSet categoriaRs = categoriaStmt.executeQuery()) {
	            if (categoriaRs.next()) {
	                categoria = new Categoria();
	                categoria.setId(categoriaRs.getInt("id"));
	                categoria.setNome(categoriaRs.getString("nome"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return categoria;
	}

	@Override
	public void update(Produto obj) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Produto> listPaginado(int pagina, int itensPorPagina) {
	    List<Produto> produtos = new ArrayList<>();
	    String sql = "SELECT * FROM produto LIMIT ? OFFSET ?";

	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, itensPorPagina); // Define o limite de itens por página
	        stmt.setInt(2, (pagina - 1) * itensPorPagina); // Define o offset

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Produto produto = new Produto();
	                produto.setId(rs.getInt("id"));
	                produto.setNome(rs.getString("nome"));
	                produto.setDescricao(rs.getString("descricao"));
	                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
	                produto.setPreco(rs.getDouble("preco"));

	                int categoriaId = rs.getInt("categoria_id");
	                Categoria categoria = findCategoriaById(categoriaId);
	                produto.setCategoria(categoria);

	                produtos.add(produto);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return produtos;
	}

	public int count() {
	    int total = 0;
	    String sql = "SELECT COUNT(*) FROM produto";

	    try (PreparedStatement stmt = conexao.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        if (rs.next()) {
	            total = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return total;
	}
	
	 public void updateEstoque(Produto produto) throws SQLException {
	        String sql = "UPDATE produto SET quantidade_estoque = ? WHERE id = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setInt(1, produto.getQuantidadeEstoque());  // Atualiza o estoque
	            stmt.setInt(2, produto.getId());  // Identifica o produto pelo ID
	            stmt.executeUpdate();
	        }
	    }

}
