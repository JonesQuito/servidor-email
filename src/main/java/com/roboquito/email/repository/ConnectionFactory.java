package com.roboquito.email.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mysql.jdbc.Connection;

public class ConnectionFactory {
	
	private static EntityManagerFactory entityManagerFactory = null;
	
	public static EntityManagerFactory getEMF() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("servidor_email");
		}
		return entityManagerFactory;
	}
	
	public EntityManager getEtityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
