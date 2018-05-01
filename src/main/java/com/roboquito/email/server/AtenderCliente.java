package com.roboquito.email.server;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.roboquito.email.connection.ConnectionFactory;
import com.roboquito.email.dao.ClienteDao;
import com.roboquito.email.model.Cliente;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import com.roboquito.email.repository.Clientes;
import com.roboquito.email.repository.ClientesRepository;
import com.roboquito.email.repository.PacotesRepository;
import com.roboquito.email.service.Util;

public class AtenderCliente implements Runnable {

	@Autowired
	Clientes clientes;
	PacotesRepository pacotesRepository;
	ClientesRepository clientesRepository;
	Object objeto;
	Socket socketCliente;
	ArrayList<ServerMethods> metodos = new ArrayList<ServerMethods>();

	public AtenderCliente(Socket cliente) {
		this.socketCliente = cliente;
		this.pacotesRepository = PacotesRepository.getInstance();
		clientesRepository = new ClientesRepository();
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
					System.out.println("SERVIDOR - Retornar lista de objetos solicitados");
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
				Cliente c = dao.pesquisarEmailAndSenha(cliente.getEmail(), cliente.getSenha());

				try {
					Util.enviarObjeto(c, socketCliente.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
