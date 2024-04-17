package br.com.autenticacao.DAO;

import java.util.List;

import br.com.autenticacao.model.Usuario;

public interface GenericDAO {

		public List<Object> listarTodos(); 
		public Object listarPorId (int id); 
		public Boolean cadastrar (Object object); 
		public Boolean alterar (Object object); 
		public void excluir (int id);
		public Boolean realizarAutenticacao(String email, String senha); 
		
}

