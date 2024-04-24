package br.com.autenticacao.DAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.autenticacao.model.Usuario;
import br.com.autenticacao.util.ConnectionFactory;


public class UsuarioDAOImpl implements GenericDAO{
	
private Connection conn; 
	
	public UsuarioDAOImpl() throws Exception { 
		try { 
			this.conn=ConnectionFactory.getConnection();
			System.out.println("Conectado com sucesso!");
			
		} catch (Exception ex) {
			throw new Exception (ex.getMessage()); 
		}
	}


	@Override
	public List<Object> listarTodos() {
		List <Object> lista = new ArrayList<Object>(); //Lista que vai retornar 
		PreparedStatement stmt =null;  //Objeto criado 
		ResultSet rs = null; //Objeto criado 
		
		String sql = "SELECT id, nome, email FROM usuario ORDER BY nome"; //Var para armazenar o select que vais er executado no banco 
		
		try { 
			stmt = conn.prepareStatement(sql);  //aqui o objetivo é transformar o sql em algo que relamwente vai ser lido pelo banco, uma sql executável, é isso que o prepareStatemet faz
			rs = stmt.executeQuery(); //esse aqui é o que vai lá no banco e realmente executa, ele vais er armazenado aqui no rs 
			while(rs.next()) { //enquanto houver linha nessa tabela ele vai pulando 
				Usuario usuario = new Usuario(); //Crio um novo produto lá da classe produto 
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));

				lista.add(usuario);
			}
			
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar usuário! Erro:" + ex.getMessage());
			ex.printStackTrace();
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt, rs);
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão! Erro:" + ex.getMessage());
			}
		}
		
		return lista; 
	}

	@Override
	public Object listarPorId(int id) {
		
		PreparedStatement stmt =null;  //Objeto criado,  usado quando precisamos executar comandos SQL pré-compilados e obter o resultado produzido.
		Usuario usuario = null; //objeto produto
		ResultSet rs =null; //objeto que representa um conjunto de dados recuperados de uma base de dados após a execução de uma consulta SQL. 
		
		
		String sql = "SELECT id, nome, email FROM usuario WHERE id=" + "(?)"; //Var para armazenar o select que vais er executado no banco 
		
		try { 
			stmt =conn.prepareStatement(sql); //converter string em sql 
		stmt.setInt(1, id); // define o valor do primeiro parâmetro (índice 1) na consulta. O valor é o id fornecido.
		stmt.executeQuery(); // executa a consulta preparada no banco de dados, mas o resultado da execução não está sendo armazenado.
		rs = stmt.executeQuery(); //a consulta preparada é executada novamente e o resultado é armazenado em um objeto do tipo ResultSet chamado rs. O ResultSet contém os resultados da consulta realizada.
		
		if (rs.next()) { //resultado (ou seja, rs.next() retorna true):
         usuario = new Usuario ();
         usuario.setId(rs.getInt("id"));
         usuario.setNome (rs.getString("nome")); 
         usuario.setEmail (rs.getString("email")); //produto recebe valores das colunas “id” e “descricao” obtidos do ResultSet.
 		JOptionPane.showMessageDialog(null, "Usuário localizado!");

        }

			} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao exibir usuário! Erro:" + ex.getMessage());
			ex.printStackTrace();
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt, rs); 
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão! Erro:" + ex.getMessage());
			}
		}
		
		return usuario; 
	}
	

	@Override
	public Boolean cadastrar(Object object) {
		Usuario usuario = (Usuario) object; 
		PreparedStatement stmt =null; // Para executar consultas parametrizadas. 
		String sql = "INSERT INTO usuario (nome, email, senha, isAtivo) " + "VALUES (?,?,MD5(?),?) "; // esse ponto de interrogacao será inserido depois e substituido. depois do descricao eu poderia colocar , mais coisas e no ponto de interrogacao mais coisas 
		
		try { 
			stmt =conn.prepareStatement(sql); //atribuído à variável "stmt" um objeto PreparedStatement criado a partir da conexão "conn" e da string "sql". Isso indica que uma consulta parametrizada está sendo preparada para execução no banco de dados.
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());//1 equivale ao primeiro ponto de interrogacao e o pdouto,getDescricao é o que vai ser colocado no lugar do ponto de interrogaçao.  Aqui está sendo definido o valor do primeiro parâmetro da consulta preparada. O método setString está sendo usado para atribuir a descrição do produto (provavelmente obtida do objeto "produto") ao primeiro parâmetro da consulta.
			stmt.setString(3, usuario.getSenha());
			stmt.setBoolean(4, usuario.getAtivo());
			
			stmt.execute(); // só executa sem um retorno execute query porque nao precis mostrar resultados de volta 
			return true; 
		} catch (SQLException ex) {
			System.out.println("problemas na DAO ao cadastrar usuário! Erro: " + ex.getMessage());
			ex.printStackTrace();
			return false; //se nao conseguiu cadastrar retorna false 
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt);
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Boolean alterar(Object object) {
		
		Usuario usuario = (Usuario) object; 
		PreparedStatement stmt =null; // Para executar consultas parametrizadas. 
		String sql = "UPDATE usuario (nome, email, senha, isAtivo) " 
		+ "VALUES (?,?,?,?,?) " 
		+ "WHERE id= ?";
		
		try { 
			stmt =conn.prepareStatement(sql); //atribuído à variável "stmt" um objeto PreparedStatement criado a partir da conexão "conn" e da string "sql". Isso indica que uma consulta parametrizada está sendo preparada para execução no banco de dados.
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());//1 equivale ao primeiro ponto de interrogacao e o pdouto,getDescricao é o que vai ser colocado no lugar do ponto de interrogaçao.  Aqui está sendo definido o valor do primeiro parâmetro da consulta preparada. O método setString está sendo usado para atribuir a descrição do produto (provavelmente obtida do objeto "produto") ao primeiro parâmetro da consulta.
			stmt.setString(3, usuario.getSenha());
			stmt.setBoolean(4, usuario.getAtivo());
			
			stmt.execute(); // só executa sem um retorno execute query porque nao precis mostrar resultados de volta 
			return true; 
		} catch (SQLException ex) {
			System.out.println("problemas na DAO ao cadastrar usuário! Erro: " + ex.getMessage());
			ex.printStackTrace();
			return false; //se nao conseguiu cadastrar retorna false 
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt);
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void excluir(int id) {

			PreparedStatement stmt =null;  //Objeto criado,  usado quando precisamos executar comandos SQL pré-compilados e obter o resultado produzido.
			
			String sql = "DELETE FROM usuario WHERE id= "+ "(?)"; 
			
			try { 
				stmt =conn.prepareStatement(sql); //converter string em sql 
				stmt.setInt(1, id);  // define o valor do primeiro parâmetro (índice 1) na consulta. O valor é o id fornecido.
				stmt.executeUpdate(); //O método executeUpdate() é usado para executar comandos SQL que alteram os dados no banco de dados.
				
		
			} catch (SQLException ex) {
				System.out.println("problemas na DAO ao excluir usuário! Erro: " + ex.getMessage());
				ex.printStackTrace();
				
			} finally { 
				try { 
					ConnectionFactory.closeConnection(conn, stmt);
				} catch (Exception ex) { 
					System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		
		
	}		
	
	
	
public Boolean realizarAutenticacao(String email, String senha) {
	ResultSet rs = null;
    PreparedStatement stmt = null;

    String sql = " SELECT id, nome FROM usuario WHERE "
    		+ " email = ? " 
    		+ " AND senha = MD5(?) ";
    
    try {
       
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);//1 equivale ao primeiro ponto de interrogacao e o pdouto,getDescricao é o que vai ser colocado no lugar do ponto de interrogaçao.  Aqui está sendo definido o valor do primeiro parâmetro da consulta preparada. O método setString está sendo usado para atribuir a descrição do produto (provavelmente obtida do objeto "produto") ao primeiro parâmetro da consulta.
        stmt.setString(2, senha);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
        	return true; 
        	
        } else { 
        	return false; 
        }
        
    } catch (Exception e) {
        System.out.println("Erro na autenticação: " + e.getMessage());
        e.printStackTrace();
        return false;
        
    } finally { 
			try { 
				
				ConnectionFactory.closeConnection(conn, stmt); 
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão! Erro:" + ex.getMessage());
			}
		}
}

public void desativar (int id) {

	PreparedStatement stmt =null;  //Objeto criado,  usado quando precisamos executar comandos SQL pré-compilados e obter o resultado produzido.
	
	String sql = "UPDATE usuario SET isAtivo = 'false'" + "WHERE id= " + "(?)";  
	
	try { 
		stmt =conn.prepareStatement(sql); //converter string em sql 
		stmt.setInt(1, id);  // define o valor do primeiro parâmetro (índice 1) na consulta. O valor é o id fornecido.
		stmt.executeUpdate(); //O método executeUpdate() é usado para executar comandos SQL que alteram os dados no banco de dados.
		

	} catch (SQLException ex) {
		System.out.println("problemas na DAO ao excluir Produto! Erro: " + ex.getMessage());
		ex.printStackTrace();
		
	} finally { 
		try { 
			ConnectionFactory.closeConnection(conn, stmt);
		} catch (Exception ex) { 
			System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
			ex.printStackTrace();
		}
	}


}


}


