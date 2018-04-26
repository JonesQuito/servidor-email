package com.roboquito.email.model;

import java.util.ArrayList;
import java.util.List;

public class ListaDePacotes {
	
	private List<Pacote> listaDePacotes = new ArrayList<>();
	
	
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
		this.listaDePacotes.add(pacote);
	}

}
