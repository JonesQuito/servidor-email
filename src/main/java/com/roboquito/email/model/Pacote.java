package com.roboquito.email.model;

import java.io.Serializable;

public class Pacote implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mensagem;
	private String remetente;
	private String destinatario;
	private ServerMethods metodo;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public ServerMethods getMetodo() {
		return metodo;
	}

	public void setMetodo(ServerMethods metodo) {
		this.metodo = metodo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

}
