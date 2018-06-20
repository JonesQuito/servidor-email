package com.roboquito.email.model;

import java.io.Serializable;
import java.util.Date;

public class Pacote implements Serializable {

	private static final long serialVersionUID = 1L;

	private String assunto;
	private byte[] mensagem;
	byte[] hashCriptografado;
	private String remetente;
	private String destinatario;
	private Date dataCriacao;
	private byte[] chaveSimetrica;
	private ServerMethods metodo;

	/*
	 * 1 - Criptografar a mensagem com AES 2 - Calcular o MD5 da mensagem original 3
	 * - Criptograr o MD5 com a chave privada do remetente 4 - Criptografar a chave
	 * simétrica usada para criptografar a mensagem. Usar a chave pública do
	 * destinatário Resultado: {Mensagem criptografada, chave simétrica
	 * criptografada, hash criptografado}
	 */

	public byte[] getMensagem() {
		return mensagem;
	}

	public void setMensagem(byte[] mensagem) {
		this.mensagem = mensagem;
	}

	public byte[] getHashCriptografado() {
		return hashCriptografado;
	}

	public void setHashCriptografado(byte[] hashCriptografado) {
		this.hashCriptografado = hashCriptografado;
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

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public byte[] getChaveSimetrica() {
		return chaveSimetrica;
	}

	public void setChaveSimetrica(byte[] chaveSimetrica) {
		this.chaveSimetrica = chaveSimetrica;
	}

}
