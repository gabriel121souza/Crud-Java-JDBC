package br.com.agenda.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	/*
	 * CRUD
	 * CREATE
	 * READ
	 * UPDATE
	 * DELETE
	 */
		//Create
		public void save(Contato contato) {
			//etapa 1 // passar o conn pstm e fazer o sql
			String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?,?,?)";
			PreparedStatement pstm = null;
			Connection conn = null;
			
			// etapa 2 conectar e inserir
		try {
			conn = (Connection) ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
		//etapa 3 fecha as conexxao
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm !=null) {
					pstm.close();
				}if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
				}
			}
		}
		
		//READ
		
		public List<Contato> getContatos(){

			//etapa etapa 1			
			String sql = "SELECT * FROM contatos";			
			List<Contato> contatos = new ArrayList<Contato>();
			Connection conn = null;
			PreparedStatement pstm = null;
		
			//Classe que vai recuperar os dados do banco. ***SELECT***
			ResultSet  rset = null;
			
			// etapa 2
			try {
				conn = (Connection) ConnectionFactory.createConnectionToMySQL();
				pstm = (PreparedStatement) conn.clientPrepareStatement(sql);
				rset = pstm.executeQuery();
				
			// etapa 3
				//separar a informacao;
				 while (rset.next()) {
					Contato contato = new Contato();
					
					//Recuperar o id
					contato.setId(rset.getInt("id"));
					
					//Recuperar o nome
					contato.setNome(rset.getString("nome"));
					
					//Recuperar a idade 
					contato.setIdade(rset.getInt("idade"));
					
					//Recuperar data de cadastro
					
					contato.setDataCadastro(rset.getNString("dataCadastro"));
					
					contatos.add(contato);
				}
				// etapa 4
			}catch(Exception e) {
				e.printStackTrace();
				
				
			}finally {
				try {
				if (rset!=null) {
					rset.close();
				}if( pstm!= null) {
					pstm.close();
				}if(conn !=null) {
					conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return contatos;

		}

		//READ BY ID
		
	
		public Contato selectById(int id) {
			//etapa 1
			String sql = "SELECT * FROM contatos WHERE id = ?";
			Contato contato = new Contato();
			Connection conn = null;
			PreparedStatement  pstm = null;
			ResultSet resultSet = null;
		//etapa 2
			try {
			conn = (Connection) ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);
			resultSet = pstm.executeQuery();
			//etapa  3
			while(resultSet.next()){
				contato.setId(resultSet.getInt("id"));
				contato.setNome(resultSet.getString("nome"));
				contato.setIdade(resultSet.getInt("idade"));
				contato.setDataCadastro(resultSet.getString("datacadastro"));
			}
			//etapa 4
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(resultSet !=null) {
						resultSet.close();
					}if(pstm != null) {
						pstm.close();
					}if(conn !=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return contato;
		}
		
		//UPDATE
		
		
		public void update(Contato contato, int id) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {
				connection = (Connection) ConnectionFactory.createConnectionToMySQL();
				preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE contatos SET nome = ? WHERE id = ?");

				preparedStatement.setString(1, contato.getNome());
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();

				System.out.println("UPDATE contatos SET nome = ? WHERE id = ?");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		//DELETE
		
		public void delete (int id) {
			String sql = "DELETE FROM contatos WHERE id =?";
			Connection conn = null;
			PreparedStatement pstm = null;
			try {
			conn = (Connection) ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.clientPrepareStatement(sql);
			
			pstm.setInt(1, id);
			pstm.executeUpdate();
			
			System.out.println("DELETE FROM contatos WHERE id= ?");
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
				if(pstm != null) {
					pstm.close();
				}if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
				}	
			}
		}
}



