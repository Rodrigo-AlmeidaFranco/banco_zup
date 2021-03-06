package br.com.bancozup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.Cliente;
import br.com.bancozup.model.Conta;

@Repository
public interface ContaRepo extends JpaRepository< Conta , Long>{
	Conta findContaByCliente( Cliente cliente );
}
