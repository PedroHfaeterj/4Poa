package br.com.faculdade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdade.conexao.Conexao;
import br.com.faculdade.model.Produto;

public class ProdutoDAO {

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        
        try (Connection conn = Conexao.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                lista.add(p);
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        return lista;
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;
        
        try (Connection conn = Conexao.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPreco(rs.getDouble("preco"));
                }
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
        }
        return produto;
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getId());
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
    }
}