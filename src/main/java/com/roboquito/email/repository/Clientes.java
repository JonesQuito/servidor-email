package com.roboquito.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roboquito.email.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>{
	
}
