package com.roboquito.email;

import java.net.Socket;

import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import com.roboquito.email.service.Util;

public class Cliente {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		Pacote pacote = new Pacote();
		Object obj = pacote;
		//pacote.setMensagem("Boa tarde professor, por gentileza considera a avaliação do dia 10/03");
		pacote.setMensagem("mensagem - 04".getBytes());
		pacote.setRemetente("Jones Quito");
		pacote.setMetodo(ServerMethods.GET_OBJECTS_BYADDRESSEE);
		
		Socket socket = new Socket("127.0.0.1", 5000);
		
		Util.enviarObjeto(pacote, socket.getOutputStream());
		
		//pacote = (Pacote)Util.lerObjecto(socket.getInputStream());
		//System.out.println("MENSAGEM RECEBIDA DO SERVIDOR: " + pacote.getMensagem());
		System.out.println("Tipo do objeto: " + obj.getClass());
		

	}

}
