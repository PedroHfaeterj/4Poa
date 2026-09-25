package br.crud.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static final String URL = "jdbc:h2:mem:todo_db;DB_CLOSE_DELAY=-1;INIT=CREATE TABLE IF NOT EXISTS tarefas (id INT AUTO_INCREMENT PRIMARY KEY, descricao VARCHAR(255), concluida BOOLEAN)";
	private static final String USER = "sa";
	private static final String PASS = "";

	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}