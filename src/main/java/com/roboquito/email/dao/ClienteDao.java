package com.roboquito.email.dao;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.roboquito.email.model.Cliente;

public class ClienteDao {

	private Connection connection;

	public ClienteDao(Connection connection) {
		this.connection = connection;
	}

	
	//PESQUISAR POR MATRICULA
	public Cliente pesquisarEmail(String email) {
		Cliente cliente = null;

		try {
			String sql = "select * from cliente where email=?";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setSenha(rs.getString("senha"));
				cliente.setId(rs.getLong("id"));

			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return cliente;
	}
	
	
	//PESQUISAR POR  EMAIL E SENHA
		public Cliente pesquisarEmailAndSenha(String email, String senha) {
			Cliente cliente = null;
			try {
				String sql = "select * from cliente where email=? and senha=?";
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setString(1, email);
				stmt.setString(2, senha);
				
				ResultSet rs = stmt.executeQuery();
				

				if (rs.next()) {
					cliente = new Cliente();
					cliente.setNome(rs.getString("nome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setSenha(rs.getString("senha"));
					cliente.setId(rs.getLong("id"));
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
			return cliente;
		}
		
		
		public void publicarChave(String email, PublicKey chave) {
			try {
				String sql = "update cliente set public_key=? where email=?";
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setObject(1, chave);
				stmt.setString(2, email);
				
				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		public PublicKey getPublicKey(String email) {
			PublicKey publicKey = null;
			try {
				String sql = "select public_key from cliente where email=?";
				PreparedStatement stmt = this.connection.prepareStatement(sql);
				stmt.setObject(1, email);

				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					publicKey = (PublicKey) rs.getObject("public_key");
				}

			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
			return publicKey;
		}
	
	


}