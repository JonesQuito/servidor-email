package com.roboquito.email.model;

import java.io.Serializable;
import java.security.PublicKey;

public class ChavePublica implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private PublicKey publicKey;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

}
