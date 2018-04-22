package com.roboquito.email.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Servidor {

	private int porta;
	private List<Socket> clientes;

	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<>();
	}

	public void executa() throws IOException  {
		try(ServerSocket servidor = new ServerSocket(this.porta)){
			System.out.println("Porta " + servidor.getLocalPort() + " aberta!");
	
			while (true) {
				Socket socket = servidor.accept();
				Thread thread = new Thread(new AtenderCliente(socket));
				thread.start();
			}
		}
	}

	public void distribuiMensagem(Socket clienteQueEnviou, String msg) {
		for (Socket cliente : this.clientes) {
			if(!cliente.equals(clienteQueEnviou)){
				try {
					PrintStream ps = new PrintStream(cliente.getOutputStream());
					ps.println(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
