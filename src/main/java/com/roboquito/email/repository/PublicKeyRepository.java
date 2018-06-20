package com.roboquito.email.repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.roboquito.email.model.ChavePublica;
import com.roboquito.email.service.Arquivo;

public class PublicKeyRepository {

	private final String ARQUIVODESTINO = "publicKey.dat";
	File arquivo = new File(ARQUIVODESTINO);

	//

	public void saveAllChaves(ArrayList<ChavePublica> allChaves) throws FileNotFoundException, IOException {
		Arquivo.writeObject(allChaves, ARQUIVODESTINO);
	}

	public ArrayList<ChavePublica> getAllChaves() throws FileNotFoundException, ClassNotFoundException, IOException {

		ArrayList<ChavePublica> listaChaves = (ArrayList<ChavePublica>) Arquivo.readListObject(ARQUIVODESTINO);
		return listaChaves;

	}

	public void addChave(ChavePublica chavePublica) throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<ChavePublica> chavesPublicas = new ArrayList<ChavePublica>();
		try {
			chavesPublicas = getAllChaves();
			if (!chavesPublicas.contains(chavePublica)) {
				chavesPublicas.add(chavePublica);
				saveAllChaves(chavesPublicas);
			}
		} catch (EOFException e) {
			chavesPublicas.add(chavePublica);
			saveAllChaves(chavesPublicas);
		}
	}

	public ChavePublica getByEmail(String email) throws FileNotFoundException, ClassNotFoundException, IOException {
		ChavePublica chaveRetorno = null;
		if (email == null)
			return null;
		ArrayList<ChavePublica> chavesPublicas = new ArrayList<ChavePublica>();
		chavesPublicas = getAllChaves();
		for (ChavePublica cp : chavesPublicas) {
			if (cp.getEmail().equals(email)) {
				chaveRetorno = cp;
			}
		}
		return chaveRetorno;
	}

	public ArrayList<ChavePublica> listar() throws IOException, ClassNotFoundException {
		ArrayList<ChavePublica> chavePublica = new ArrayList<>();

		InputStream is = new FileInputStream(ARQUIVODESTINO);
		ObjectInputStream readerObject = new ObjectInputStream(is);
		chavePublica = (ArrayList<ChavePublica>) readerObject.readObject();
		is.close();

		return chavePublica;
	}
}
