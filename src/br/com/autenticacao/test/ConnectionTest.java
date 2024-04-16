package br.com.autenticacao.test;

import java.sql.Connection;

import br.com.autenticacao.util.ConnectionFactory;

public class ConnectionTest {

public static void main(String[] args) {
	
		try { 
			
			Connection connection = ConnectionFactory.getConnection();  //Aqui voce busca o conection do pacote projetoMvc e 
			if (connection != null) {
				System.out.println("Conexão estabelecida com sucesso!");
			}
			
			connection.close(); 
		} catch (Exception e) {
			System.out.println("Erro ao conectar: " + e.getMessage());
			e.printStackTrace();
			
		}
	}
}
