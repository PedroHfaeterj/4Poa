package br.com.faculdade.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conexao {
    
    private static final String URL = "jdbc:h2:file:~/meu_ecommerce_db";
    private static final String USER = "sa";
    private static final String PASS = "";
    
  
    private static boolean bancoInicializado = false;

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            
          
            if (!bancoInicializado) {
                inicializarBancoDeDados(conn);
                bancoInicializado = true;
            }
            
            return conn;
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        }
    }

    private static void inicializarBancoDeDados(Connection conn) {
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
                           + "id INT AUTO_INCREMENT PRIMARY KEY, "
                           + "email VARCHAR(255), "
                           + "senha VARCHAR(255))";

        String sqlProdutos = "CREATE TABLE IF NOT EXISTS produtos ("
                           + "id INT AUTO_INCREMENT PRIMARY KEY, "
                           + "nome VARCHAR(255), "
                           + "preco DOUBLE)";

      
        String sqlAdmin = "MERGE INTO usuarios (email, senha) KEY(email) "
                        + "VALUES ('admin@loja.com', '123456')";

        try (PreparedStatement stmt1 = conn.prepareStatement(sqlUsuarios);
             PreparedStatement stmt2 = conn.prepareStatement(sqlProdutos);
             PreparedStatement stmt3 = conn.prepareStatement(sqlAdmin)) {
             
            stmt1.execute();
            stmt2.execute();
            stmt3.execute();
            System.out.println("-> Tabelas verificadas e Administrador configurado com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro ao inicializar banco de dados: " + e.getMessage());
        }
    }
}