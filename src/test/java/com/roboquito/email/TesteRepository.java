package com.roboquito.email;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.roboquito.email.model.Cliente;
import com.roboquito.email.model.ServerMethods;
import com.roboquito.email.service.Util;

public class TesteRepository {


	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Cliente cliente = new Cliente();
		cliente.setEmail("fernandadhy@hotmail.com");
		cliente.setSenha("123456");
		cliente.setMetodo(ServerMethods.AUTENTICAR_USUARIO);
		
		Socket socket = new Socket("127.0.0.1", 5000);
		
		Util.enviarObjeto(cliente, socket.getOutputStream());
		
		Cliente c = (Cliente) Util.lerObjecto(socket.getInputStream());
		//Cliente c = (Cliente) Util.lerObjecto(socket.getInputStream());
		
		System.out.println(c.getNome());
		
	}

}
