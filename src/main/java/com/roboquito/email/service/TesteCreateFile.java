package com.roboquito.email.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TesteCreateFile {

	private static final String ARQUIVODESTINO = "C:/publickey/publicKey.dat";
    private static File arquivo = new File(ARQUIVODESTINO);
    
	public static void main(String[] args) throws IOException {
		Arquivo.gravarTxt("asdfdffsdfsd", "TESTE.dat");
		Path path = Paths.get("C:/publickey/publicKey.dat");
		File file = new File("TESTE.dat");
		if(!file.exists()) {
			file.createNewFile();
		}


	}

}
