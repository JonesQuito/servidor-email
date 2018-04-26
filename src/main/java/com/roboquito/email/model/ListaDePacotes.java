package com.roboquito.email.model;

import java.util.ArrayList;
import java.util.List;

public class ListaDePacotes {
	
	private static List<Pacote> listaDePacotes = new ArrayList<>();
	private static ListaDePacotes instance = null;
	
	private ListaDePacotes() {}
	
	public static ListaDePacotes getInstance() {
		if(instance == null) {
			instance = new ListaDePacotes();
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
	
	
	public void addPacote(Pacote pacote) {
		listaDePacotes.add(pacote);
	}

}
