package com.roboquito.email.server;

import java.net.Socket;
import java.util.ArrayList;

import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import com.roboquito.email.repository.PacotesRepository;
import com.roboquito.email.service.Util;

public class AtenderCliente implements Runnable {

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
		try {
			Pacote msg = (Pacote) objeto;
			if (msg != null) {
				switch (msg.getMetodo()) {
				case SAVE_OBJECT:
					pacotesRepository.addPacote(msg);
					System.out.println("SERVIDOR - O pacote foi salvo no servidor");
					break;

				case GET_ALL_OBJECTS:
					for(Pacote p: pacotesRepository.getAllPackages()) {
						System.out.println(p.getMensagem());
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

			//System.out.println("SERVIDOR - " + msg.getMensagem());
			//msg.setMensagem("Pode ficar tranquilo que a avaliação será considerada!");
			//Util.enviarObjeto(msg, socketCliente.getOutputStream());
			//System.out.println("SERVIDOR - Pacote encaminhado para o cliente");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
