package com.roboquito.email;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.roboquito.email.model.Cliente;
import com.roboquito.email.service.Util;

public class TesteRepository {


	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Cliente cliente = new Cliente();
		cliente.setEmail("jonesdhy@hotmail.com");
		cliente.setSenha("123456");
		
		Socket socket = new Socket("127.0.0.1", 5000);
		
		Util.enviarObjeto(cliente, socket.getOutputStream());
		
		List<Cliente> clientes = (List<Cliente>) Util.lerObjecto(socket.getInputStream());
		//Cliente c = (Cliente) Util.lerObjecto(socket.getInputStream());
		
		for(Cliente c : clientes) {
			System.out.println(c.getNome());
		}
		
	}

}
