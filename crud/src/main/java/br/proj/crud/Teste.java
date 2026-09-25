package br.proj.crud;

import java.sql.Connection;
import java.sql.DriverManager;


public class Teste{
	public static void main(String[] args) {
		
		try {
			Connection conn =
					DriverManager.getConnection(
							"jdbc:h2:./tarefasdb",
							"sa",
							""
							);
			System.out.println("Conectado com sucesso!");
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}