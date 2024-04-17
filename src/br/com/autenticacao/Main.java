package br.com.autenticacao;

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
				.concat("[6] Finalizar\n")
				.concat("Digite a opção desejada: \n");
		
			String option = JOptionPane.showInputDialog(menu);
			int optionInt = Integer.parseInt(option);

		
		UsuarioController controller = new UsuarioController(); 

		while (optionInt!=0) {
		int idModificar;
		switch (optionInt){
		
		case 1: 
				//Cadastrar 
				Usuario novoUsuario = new Usuario(); 
				novoUsuario.setNome(JOptionPane.showInputDialog("Nome: "));
				novoUsuario.setEmail(JOptionPane.showInputDialog("Email: "));
				novoUsuario.setSenha(JOptionPane.showInputDialog("Senha: "));
				String input = JOptionPane.showInputDialog("Ativo/inativo: ");
				boolean isAtivo = "ativo".equalsIgnoreCase(input);
				novoUsuario.setAtivo(isAtivo);


				controller.cadastrar(novoUsuario);
				break; 
				
			case 2: 
	
				//Listar 
				Usuario usuario = new Usuario(); 

				List <Usuario>lista = new ArrayList<Usuario>(); 
				lista = controller.listarTodos();

	
				String mensagemLista=" "
						.concat("-Lista Usuarios-")
						.concat("\n")
						.concat("Cód. Descrição"); 

				for (Usuario produto : lista) {
					mensagemLista=mensagemLista
		.concat("\n")
		.concat(String.valueOf(produto.getId())) //concat só recebe string entao preciso converter 
		.concat("            ")
		.concat(usuario.getNome())
		.concat("\n")
		.concat(String.valueOf(usuario.getEmail()))
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
							.concat(usuarioEncontrado.getNome()); 
					
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
				idModificar = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do produto a ser modificado: ")); 
				String  novaDescricao = JOptionPane.showInputDialog("Digite a nova descrição: "); 

				Usuario usuarioAlterado = new Usuario(); 
				usuarioAlterado.setNome(JOptionPane.showInputDialog("Nome: "));
				usuarioAlterado.setEmail(JOptionPane.showInputDialog("Email: "));
				usuarioAlterado.setSenha(JOptionPane.showInputDialog("Senha: "));
				String inputAlterado = JOptionPane.showInputDialog("Ativo/inativo: ");
				boolean isAtivoAlterado = "ativo".equalsIgnoreCase(inputAlterado);
				usuarioAlterado.setAtivo(isAtivoAlterado);

				
				controller.alterar(usuarioAlterado);
				break; 
				
			case 6: 
				JOptionPane.showMessageDialog(null, "Sistema finalizado!");
				break; 
	
				default: 
					JOptionPane.showMessageDialog(null, "Opção inexistente \n Tente novamente!");
					break; 
			}
		if (optionInt!=6) {
		option = JOptionPane.showInputDialog(menu);
		optionInt = Integer.parseInt(option);
		} else { 
			break; 
		}
		}
	}
	}
