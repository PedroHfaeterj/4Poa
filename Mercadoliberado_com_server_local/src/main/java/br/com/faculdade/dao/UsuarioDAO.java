package br.com.faculdade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.faculdade.conexao.Conexao;
import br.com.faculdade.model.Usuario;

public class UsuarioDAO {

    public Usuario validarLogin(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        Usuario usuarioEncontrado = null;
        
        try (Connection conn = Conexao.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setId(rs.getInt("id"));
                    usuarioEncontrado.setEmail(rs.getString("email"));
                    usuarioEncontrado.setSenha(rs.getString("senha"));
                }
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao validar login: " + e.getMessage());
        }
        
        return usuarioEncontrado;
    }
}