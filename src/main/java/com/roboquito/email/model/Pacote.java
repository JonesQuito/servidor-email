package com.roboquito.email.model;

import java.io.Serializable;
import java.util.Date;

public class Pacote implements Serializable {

	private static final long serialVersionUID = 1L;

	private String assunto;
    private String mensagem;
    private String remetente;
    private String destinatario;
    private Date dataCriacao;
    private byte[] chaveSimetrica;
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
