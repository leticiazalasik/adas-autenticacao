package br.com.autenticacao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.autenticacao.controller.UsuarioController;
import br.com.autenticacao.model.Usuario;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 

		String menu=" "
				.concat("-Opcoes- \n")
				.concat("[1] Cadastrar\n")
				.concat("[2] Listar Todos\n")
				.concat("[3] Listar por ID\n")
				.concat("[4] Excluir\n")
				.concat("[5] Editar\n")
				.concat("[6] Autenticar\n")
				.concat("[7] Desativar\n"
				.concat("[8] Listar usuários ativos\n")
				.concat("[9] Finalizar\n")
				.concat("Digite a opção desejada: \n"));
		
			String option = JOptionPane.showInputDialog(menu);
			int optionInt = Integer.parseInt(option);

			Usuario novoUsuario = new Usuario();    
        
		UsuarioController controller = new UsuarioController(); 
		
		
		//Os outros métodosn
		while (optionInt!=0) {
			int idModificar;
			switch (optionInt){
			
			case 1: 
					//Cadastrar 
					
					novoUsuario.setNome(JOptionPane.showInputDialog("Nome: "));
					novoUsuario.setEmail(JOptionPane.showInputDialog("Email: "));
					novoUsuario.setSenha(JOptionPane.showInputDialog("Senha: "));
					novoUsuario.setisativo(Boolean.valueOf(JOptionPane.showInputDialog("Ativo (true/false): ")));					

					controller.cadastrar(novoUsuario);
					break; 
					
				case 2: 
		
					//Listar 
					Usuario usuario = new Usuario(); 

					List <Usuario>lista = new ArrayList<Usuario>(); 
					lista = controller.listarTodos();

		
					String mensagemLista=" "
							.concat("-Lista Usuarios-")
							.concat("\n");
					for (Usuario usuario1 : lista) {
						mensagemLista=mensagemLista
			.concat("\n")
			.concat(String.valueOf(usuario1.getId())) //concat só recebe string entao preciso converter 
			.concat(" -")
			.concat(usuario1.getNome())
			.concat("\n")
			.concat(String.valueOf(usuario1.getEmail()))
			.concat("\n")
			.concat(String.valueOf("Ativo? "+ (usuario1.getisativo())))
			.concat("\n");
			//.concat(Boolean.valueOf(usuario.getAtivo())); 
		
					}
					JOptionPane.showMessageDialog(null, mensagemLista);
					break; 
					
				case 3: 
				//Listar por id	
					
					int opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o id: ")); 
									
					Usuario usuarioEncontrado = controller.listarPorId(opcao);

					if (usuarioEncontrado != null) {
						String mensagemLista2=" "
								.concat("Id: ") 
								.concat(String.valueOf(usuarioEncontrado.getId())) //concat só recebe string entao preciso converter 
								.concat("\n")
								.concat("Nome: ")
								.concat(usuarioEncontrado.getNome())
								.concat("\n")
								.concat(String.valueOf("Ativo? "+ (usuarioEncontrado.getisativo()))); 
						
						JOptionPane.showMessageDialog(null, mensagemLista2);
					} else {
						JOptionPane.showMessageDialog(null, "Não existe produto com esse código na lista");
					}
					
					break; 
					
				case 4: 
					//Excluir 
					opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o id: ")); 
					
					if ( controller.excluir(opcao)) { 
						JOptionPane.showMessageDialog(null, "Usuario excluído com sucesso!");

					} else { 
					controller.excluir(opcao);
					JOptionPane.showMessageDialog(null, "Erro ao excluir o produto " + opcao);

					}


					break; 
		
				case 5: 
					//Alterar 
					Usuario usuarioAlterado = new Usuario(); 

					idModificar = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto a ser modificado: ")); 
					usuarioAlterado.setId(idModificar);
					usuarioAlterado.setNome(JOptionPane.showInputDialog("Nome: "));
					usuarioAlterado.setEmail(JOptionPane.showInputDialog("Email: "));
					usuarioAlterado.setSenha(JOptionPane.showInputDialog("Senha: "));
					String inputAlterado = JOptionPane.showInputDialog("Ativo/inativo: ");
					boolean isAtivoAlterado = "ativo".equalsIgnoreCase(inputAlterado);
					usuarioAlterado.setisativo(isAtivoAlterado);

					
					controller.alterar(usuarioAlterado);
					break; 
				
				case 6: 
					//Autenticar 
					novoUsuario.setEmail(JOptionPane.showInputDialog("Email: "));
					novoUsuario.setSenha(JOptionPane.showInputDialog("Senha: "));
					
					controller.realizarAutenticacao(novoUsuario.getEmail(), novoUsuario.getSenha()); 
					break; 
					
				case 7: 
					idModificar = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto a ser desativado: ")); 
					novoUsuario.setId(idModificar);
					controller.desativar(idModificar);
					break; 
					
				case 8: 
					usuario = new Usuario(); 

					lista = controller.listarAtivos();

		
					String mensagemLista1=" "
							.concat("-Lista Usuarios ATIVOS -")
							.concat("\n");
					for (Usuario usuario1 : lista) {
						mensagemLista1=mensagemLista1
			.concat("\n")
			.concat(String.valueOf(usuario1.getId())) //concat só recebe string entao preciso converter 
			.concat(" -")
			.concat(usuario1.getNome())
			.concat("\n")
			.concat(String.valueOf(usuario1.getEmail()))
			.concat("\n")
			.concat(String.valueOf("Ativo? "+ (usuario1.getisativo())))
			.concat("\n");
			//.concat(Boolean.valueOf(usuario.getAtivo())); 
		
					}
					JOptionPane.showMessageDialog(null, mensagemLista1);
					break; 
					
				case 9: 
					JOptionPane.showMessageDialog(null, "Sistema finalizado.");
					break; 
		
					default: 
						JOptionPane.showMessageDialog(null, "Opção inexistente \n Tente novamente!");
						break; 
						
				}
			if (optionInt!=9) {
			option = JOptionPane.showInputDialog(menu);
			optionInt = Integer.parseInt(option);
			} else { 
				break; 
			}
			}
//

		
	}
	}
