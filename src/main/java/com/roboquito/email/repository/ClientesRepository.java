package com.roboquito.email.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.roboquito.email.model.Cliente;

public class ClientesRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EntityManager em = ConnectionFactory.getEMF().createEntityManager();
	
	public List<Cliente> getAll(){
		return em.createQuery("from Cliente", Cliente.class).getResultList();
	}
	
	
	public List<Cliente> getClienteByEmail(String email) {
		List resultList = em.createQuery("from Cliente where email = '"+email+"'").getResultList();
		return resultList;
	}
	
	public Cliente getClienteByEmailAndSenha(String email, String senha) {
		return (Cliente) em.createQuery("from Cliente where email = '"+email+"' and senha = '"+senha+"'").setMaxResults(1).getSingleResult();
	}
	

	

}
