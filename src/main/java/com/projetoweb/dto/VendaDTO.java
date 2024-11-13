package com.projetoweb.dto;

import com.projetoweb.model.Cliente;
import com.projetoweb.model.ItemVenda;
import com.projetoweb.model.Produto;
import com.projetoweb.model.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaDTO implements IDTO<Venda> {

    private Connection conexao;

    public VendaDTO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void save(Venda venda) {
        String sqlVenda = "INSERT INTO venda (data, cliente_id) VALUES (?, ?)";  // Removendo valor_total
        String sqlItemVenda = "INSERT INTO item_venda (venda_id, produto_id, quantidade, preco_unitario, valor_total) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmtVenda = conexao.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
            stmtVenda.setTimestamp(1, new java.sql.Timestamp(venda.getData().getTime()));  // Usando Timestamp para incluir hora
            stmtVenda.setInt(2, venda.getCliente().getId());  // Definindo o ID do cliente

            stmtVenda.executeUpdate();

            try (ResultSet generatedKeys = stmtVenda.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    venda.setId(generatedKeys.getInt(1));  // Definindo o ID da venda após o insert
                }
            }

            // Inserindo os itens da venda
            for (ItemVenda item : venda.getItens()) {
                try (PreparedStatement stmtItem = conexao.prepareStatement(sqlItemVenda)) {
                    stmtItem.setInt(1, venda.getId());  // A venda já tem um ID agora
                    stmtItem.setInt(2, item.getProduto().getId());
                    stmtItem.setInt(3, item.getQuantidade());
                    stmtItem.setDouble(4, item.getPrecoUnitario());
                    stmtItem.setDouble(5, item.getValorTotal());

                    stmtItem.executeUpdate();
                    
                    // Atualizar o estoque do produto (se necessário)
                    ProdutoDTO produtoDTO = new ProdutoDTO(conexao);
                    Produto produto = item.getProduto();
                    produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade()); // Atualizando o estoque
                    produtoDTO.update(produto);  // Atualizando o produto no banco
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Venda> list() {
        List<Venda> vendas = new ArrayList<>();
        String sqlVenda = "SELECT * FROM venda";
        String sqlItens = "SELECT * FROM item_venda WHERE venda_id = ?";

        try (PreparedStatement stmtVenda = conexao.prepareStatement(sqlVenda);
             ResultSet rsVenda = stmtVenda.executeQuery()) {

            while (rsVenda.next()) {
                Venda venda = new Venda();
                venda.setId(rsVenda.getInt("id"));
                venda.setData(rsVenda.getDate("data"));
                venda.setValorTotal(rsVenda.getDouble("valor_total"));

                int clienteId = rsVenda.getInt("cliente_id");
                ClienteDTO clienteDTO = new ClienteDTO(conexao);
                Cliente cliente = clienteDTO.findById(clienteId);
                venda.setCliente(cliente);

                List<ItemVenda> itens = new ArrayList<>();
                try (PreparedStatement stmtItens = conexao.prepareStatement(sqlItens)) {
                    stmtItens.setInt(1, venda.getId());

                    try (ResultSet rsItens = stmtItens.executeQuery()) {
                        while (rsItens.next()) {
                            ItemVenda item = new ItemVenda();
                            item.setQuantidade(rsItens.getInt("quantidade"));
                            item.setPrecoUnitario(rsItens.getDouble("preco_unitario"));
                            item.setValorTotal(rsItens.getDouble("valor_total"));

                            int produtoId = rsItens.getInt("produto_id");
                            ProdutoDTO produtoDTO = new ProdutoDTO(conexao);
                            Produto produto = produtoDTO.findById(produtoId);
                            item.setProduto(produto);

                            itens.add(item);
                        }
                    }
                }
                venda.setItens(itens);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas;
    }

    @Override
    public void update(Venda venda) {
    }

    @Override
    public void delete(int id) {
        String sqlDeleteItens = "DELETE FROM item_venda WHERE venda_id = ?";
        String sqlDeleteVenda = "DELETE FROM venda WHERE id = ?";

        try {
            try (PreparedStatement stmtItens = conexao.prepareStatement(sqlDeleteItens)) {
                stmtItens.setInt(1, id);
                stmtItens.executeUpdate();
            }

            try (PreparedStatement stmtVenda = conexao.prepareStatement(sqlDeleteVenda)) {
                stmtVenda.setInt(1, id);
                stmtVenda.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}