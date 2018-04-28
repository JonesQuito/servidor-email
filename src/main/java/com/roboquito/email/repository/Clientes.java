package com.roboquito.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roboquito.email.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long>{

}
