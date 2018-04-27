package com.roboquito.email.repository;

import java.util.ArrayList;
import java.util.List;

import com.roboquito.email.model.Pacote;

public class PacotesRepository {
	
	private static List<Pacote> listaDePacotes = new ArrayList<>();
	private static PacotesRepository instance = null;
	
	private PacotesRepository() {}
	
	public static PacotesRepository getInstance() {
		if(instance == null) {
			instance = new PacotesRepository();
		}
		return instance;
	}
	
	public void excluirByDestinatario(String destinatario) {
		for (Pacote pacote : listaDePacotes) {
			if(pacote.getDestinatario().equals(destinatario)) {
				listaDePacotes.remove(pacote);
			}
		}
	}
	
	public void excluirByPacote(Pacote pacote) {
		listaDePacotes.remove(pacote);
	}
	
	
	public List<Pacote> getPacotesByDestinatario(String destinatario){
		List<Pacote> myPacotes = new ArrayList<>();
		
		for (Pacote pacote : listaDePacotes) {
			if(pacote.getDestinatario().equals(destinatario)) {
				myPacotes.add(pacote);
			}
		}
		return myPacotes;
	}
	
	public List<Pacote> getAllPackages(){
		return listaDePacotes;
	}
	
	
	public void addPacote(Pacote pacote) {
		listaDePacotes.add(pacote);
	}

}
