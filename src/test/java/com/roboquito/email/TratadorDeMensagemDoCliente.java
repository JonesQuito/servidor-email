package com.roboquito.email;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.roboquito.email.server.Servidor;

class TratadorDeMensagemDoCliente implements Runnable {

	private Socket cliente;
	private Servidor servidor;

	public TratadorDeMensagemDoCliente(Socket cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}

	public void run() {
		try(Scanner s = new Scanner(this.cliente.getInputStream())) {
			while (s.hasNextLine()) {
				servidor.distribuiMensagem(this.cliente, s.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}