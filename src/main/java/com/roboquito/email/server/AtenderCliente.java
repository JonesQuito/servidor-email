package com.roboquito.email.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.security.PublicKey;
import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.roboquito.email.connection.ConnectionFactory;
import com.roboquito.email.dao.ClienteDao;
import com.roboquito.email.model.ChavePublica;
import com.roboquito.email.model.Cliente;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import com.roboquito.email.repository.Clientes;
import com.roboquito.email.repository.PacotesRepository;
import com.roboquito.email.repository.PublicKeyRepository;
import com.roboquito.email.service.Util;

public class AtenderCliente implements Runnable {

	@Autowired
	Clientes clientes;
	PacotesRepository pacotesRepository;
	Object objeto;
	Socket socketCliente;
	ArrayList<ServerMethods> metodos = new ArrayList<ServerMethods>();

	public AtenderCliente(Socket cliente) {
		this.socketCliente = cliente;
		this.pacotesRepository = PacotesRepository.getInstance();
		for (ServerMethods sm : ServerMethods.values()) {
			metodos.add(sm);
		}

		try {
			objeto = Util.lerObjecto(cliente.getInputStream());
		} catch (Exception e) {
			System.out.println("SERVIDOR - Erro na leitura do objeto" + e);
		}
	}

	@Override
	public void run() {

		if (objeto.getClass().equals(Pacote.class)) {

			Pacote msg = (Pacote) objeto;
			if (msg != null) {

				switch (msg.getMetodo()) {
				case SAVE_OBJECT:
					pacotesRepository.addPacote(msg);
					System.out.println("SERVIDOR - O pacote foi salvo no servidor");
					break;

				case GET_ALL_OBJECTS:
					try {
						Util.enviarObjeto(pacotesRepository.getAllPackages(), socketCliente.getOutputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case GET_OBJECTS_BYADDRESSEE:
					try {
						Util.enviarObjeto(pacotesRepository.getPacotesByDestinatario(msg.getDestinatario()),
								socketCliente.getOutputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case GET_PUBLIC_KEY:

					break;

				default:
					System.err.println("SERVIDOR - Não foi informado um método válido");
					break;
				}
			}

		} else if (objeto.getClass().equals(Cliente.class)) {

			Cliente cliente = (Cliente) objeto;

			if (cliente != null) {
				Connection connection = new ConnectionFactory().getConnection();
				ClienteDao dao = new ClienteDao(connection);

				switch (cliente.getMetodo()) {
				case AUTENTICAR_USUARIO:

					Cliente c = dao.pesquisarEmailAndSenha(cliente.getEmail(), cliente.getSenha());

					try {
						Util.enviarObjeto(c, socketCliente.getOutputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case PUBLICAR_CHAVE:
					if(cliente.getEmail() != null && cliente.getPublickey() != null) {
						ChavePublica cp = new ChavePublica();
						cp.setEmail(cliente.getEmail());
						cp.setPublicKey(cliente.getPublickey());
						try {
							new PublicKeyRepository().addChave(cp);
						} catch (ClassNotFoundException e1) {
							System.out.println("Não foi possível encontrar a classe de Chave Pública");
							e1.printStackTrace();
						} catch (FileNotFoundException e) {
							System.out.println("Não foi possível encontrar o arquivo de Chave Pública");
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						System.out.println("Email e/ou chave são nulos");
					}

					break;

				case GET_PUBLIC_KEY:
					try {
						ChavePublica chavePublica = new PublicKeyRepository().getByEmail(cliente.getEmail());
						PublicKey pk = null;
						if(chavePublica != null) {
							pk = chavePublica.getPublicKey();
						}
						Util.enviarObjeto(pk, socketCliente.getOutputStream());
						
					} catch (FileNotFoundException e) {
						System.out.println("Arquivo de chave pública não foi encontrado");
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						System.out.println("Não foi possível encontrar a classe de Chave Pública");
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("Erro na leitura do arquivo de chave pública");
						e.printStackTrace();
					}

					break;

				default:
					System.err.println("SERVIDOR - Não foi informado um método válido");
					break;
				}

			}
		}
	}
}
