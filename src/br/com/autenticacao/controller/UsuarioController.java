package br.com.autenticacao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.autenticacao.DAO.GenericDAO;
import br.com.autenticacao.DAO.UsuarioDAOImpl;
import br.com.autenticacao.model.Usuario;


public class UsuarioController {

	Usuario usuario = null; // Inicializa o usuario como null


	public List<Usuario> listarTodos(){ //método que retorna uma lista de objetos do tipo usuario 
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>(); //Inicializacao da lista 
		try {  
			GenericDAO dao = new UsuarioDAOImpl(); //Interface nome new nome da classe que implementou - a diferenca é que nesse caso só posso usar métofos da genericDAO e se quiser usar de tudo coloca usuarioDAOIMpl mesmo 
		
			for (Object object : dao.listarTodos()) {  //a lista aqui é aquela lá do UsuarioDAOImpl esses objetos são genericos 
				listaUsuarios.add((Usuario) object); //Aqui converte os objetos genéricos pra objetos Usuarios 
				
			}
			
		} catch (Exception e) {  //Se der erro faz isso 
			System.out.println("Erro na controller ao listar Usuário.");
			e.printStackTrace();
		}
		
		return listaUsuarios; 
	}
	
public Usuario listarPorId(int id){ //método que retorna uma lista de objetos do tipo usuario 

		try {  
			GenericDAO dao = new UsuarioDAOImpl(); //instância de UsuarioDAOImpl, que implementa a interface GenericDAO, é criada e atribuída à variável dao. polimorfismo para tratar um objeto concreto (UsuarioDAOImpl) como se fosse do tipo genérico (GenericDAO).

			Usuario usuario = (Usuario) dao.listarPorId(id);  //Aqui estamos chamando o método listarPorId do objeto dao, que deveria retornar um objeto do tipo Usuario. O resultado dessa chamada é armazenado na variável usuario. 			
			return usuario; 
			
		} catch (Exception e) {  //Se der erro faz isso 
			System.out.println("Erro na controller ao listar Usuário.");
			e.printStackTrace();
			return null; 
		}
		
	}


public void cadastrar(Usuario usuario){ //método para cadastrar usuario 
		
		List<Usuario> listaUsuarios = new ArrayList<Usuario>(); //Inicializacao da lista 
		try {  
			GenericDAO dao = new UsuarioDAOImpl(); //Interface nome new nome da classe que implementou - a diferenca é que nesse caso só posso usar métofos da genericDAO e se quiser usar de tudo coloca usuarioDAOIMpl mesmo 
		if (dao.cadastrar(usuario)) {
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
			} else { 
				JOptionPane.showMessageDialog(null, "Problemas ao cadatsrar usuário");
			}
			
		} catch (Exception e) {  //Se der erro faz isso 
			System.out.println("Erro ao controller ao cadastrar Usuário.");
			e.printStackTrace();
		}
		
	}

public boolean excluir(int id ){ //método para cadastrar usuario 
	
	try {  
		GenericDAO dao = new UsuarioDAOImpl(); 
		if (validarId(usuario.getId())==false) {
				
		return false; 
		} 
		
		dao.excluir(id);
		return true; 
		
	} catch (Exception e) {  //Se der erro faz isso 
		System.out.println("Erro ao controller ao Excluir usuário.");
		e.printStackTrace();
		return false; 
}
} 

public void alterar (Usuario usuario){ //método para cadastrar usuario 

try {  
	GenericDAO dao = new UsuarioDAOImpl(); //Interface nome new nome da classe que implementou - a diferenca é que nesse caso só posso usar métofos da genericDAO e se quiser usar de tudo coloca usuarioDAOIMpl mesmo 

	if (validarId(usuario.getId())==false) {
		JOptionPane.showMessageDialog(null, "Nenhum usuario encontrado para o ID"+ usuario.getId());
	return; 
	}
	
	if (dao.alterar(usuario)) { 
		JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso.");
	} else { 
		JOptionPane.showMessageDialog(null, "Erro ao alterar usuário.");

	}
		
	
} catch (Exception e) {  //Se der erro faz isso 
	System.out.println("Erro ao controller ao Excluir usuario Usuário.");
	e.printStackTrace();

}
}

private boolean validarId (int id) { 
	
	try { 
		GenericDAO dao = new UsuarioDAOImpl(); //Interface nome new nome da classe que implementou - a diferenca é que nesse caso só posso usar métofos da genericDAO e se quiser usar de tudo coloca usuarioDAOIMpl mesmo 
		
		Usuario usuario = (Usuario) dao.listarPorId(id);
		
		if (usuario==null) { 
			return false; 
		} else { 
			return true; 
		}
	} catch (Exception e){
		System.out.println("Erro ao controller ao Excluir usuario Usuario.");
		e.printStackTrace();
		return false; 
		
	}
}

public boolean realizarAutenticacao(String email, String senha) {
    try {
        GenericDAO dao = new UsuarioDAOImpl();
         if (dao.realizarAutenticacao(email, senha) ==true) {
        	 return true; 
         } else { 
        	 return false; 
         }
    } catch (Exception e) {
        System.out.println("Erro na controller ao autenticar Usuário.");
        e.printStackTrace();
        return false; 
    }
}


}
