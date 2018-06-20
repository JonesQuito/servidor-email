package com.roboquito.email.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Arquivo {

	public static File abrirArquivo() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (fc.showOpenDialog(null) == 1) {
			return null;
		}
		File file = fc.getSelectedFile();
		return file;
	}

	public static String lerArquivoTxt(File arquivo) throws FileNotFoundException, IOException {
		FileReader reader = new FileReader(arquivo);
		BufferedReader buffer = new BufferedReader(reader);

		String linha;
		String texto = new String();

		// Lê linha a linha e adiciona em uma variável chamada 'texto'
		while (buffer.ready()) {
			linha = buffer.readLine();
			texto += linha + "\n";
		}

		buffer.close();
		reader.close();

		return texto;
	}

	public static void gravarTxt2(Path caminho, String texto) throws IOException {
		byte[] textoEmBytes = texto.getBytes();
		Files.write(caminho, textoEmBytes);
	}

	public static void gravarTxt(String texto, String destino) throws IOException {
		// Cria arquivo
		File file = new File(destino);
		// Se o arquivo nao existir, ele gera
		if (!file.exists()) {
			file.createNewFile();
		}
		// Prepara para escrever no arquivo
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		// Escreve e fecha arquivo
		bw.write(texto);
		bw.close();
	}

	// Escreve um array de bytes em um arquivo
	public static void writeArrayOfBytes(byte[] textoEmBytes, File file) throws IOException {
		Path caminho = Paths.get(file.getPath());
		Files.write(caminho, textoEmBytes);
	}

	// Escreve um array de bytes em um arquivo
	public static byte[] readAllBytes(File file) throws IOException {
		Path caminho = Paths.get(file.getPath());
		return Files.readAllBytes(caminho);
	}

	// Grava um objeto no arquivo
	public static void writeObject(Object objeto, String caminhoDoArquivo) throws FileNotFoundException, IOException {

		FileOutputStream arquivoDestino = new FileOutputStream(caminhoDoArquivo);
		ObjectOutputStream objetoDestino = new ObjectOutputStream(arquivoDestino);
		objetoDestino.writeObject(objeto);
		objetoDestino.flush();
		objetoDestino.close();
		arquivoDestino.flush();
		arquivoDestino.close();
	}

	public static Object lerObject(String caminhoDoArquivo)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		Object objetoRetorno = null;
		
		// Cria arquivo
        File file = new File(caminhoDoArquivo);
        // Se o arquivo nao existir, ele gera
        if (!file.exists()) {
            file.createNewFile();
        }

		FileInputStream arquivoOrigem = new FileInputStream(caminhoDoArquivo);
		try (ObjectInputStream objetoOrigem = new ObjectInputStream(arquivoOrigem)) {
			objetoRetorno = objetoOrigem.readObject();
		}

		return objetoRetorno;
	}

	// Ler lista de objetos do Arquivo
	public static ArrayList readListObject(String caminhoDoArquivo)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<Object> listaDeObjetos = new ArrayList<>();
		
		File file = new File(caminhoDoArquivo);
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileInputStream arquivoOrigem = new FileInputStream(caminhoDoArquivo);
		ObjectInputStream objetoOrigem = new ObjectInputStream(arquivoOrigem);
		listaDeObjetos = (ArrayList<Object>) objetoOrigem.readObject();
		objetoOrigem.close();

		return listaDeObjetos;
	}

}