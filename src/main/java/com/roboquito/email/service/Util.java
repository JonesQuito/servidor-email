package com.roboquito.email.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Util {

	public static Object lerObjecto(InputStream inputStream) throws IOException, ClassNotFoundException {
		Object objetoRetorno = null;
		ObjectInputStream objetoOrigem = new ObjectInputStream(inputStream);
		objetoRetorno = objetoOrigem.readObject();
		return objetoRetorno;
	}
	
	
	public static void enviarObjeto(Object objeto, OutputStream outputStream) throws IOException {
		ObjectOutputStream objetoDestino = new ObjectOutputStream(outputStream);
        objetoDestino.writeObject(objeto);
        objetoDestino.flush();
        //objetoDestino.close();
		
	}

}
