package br.com.bancozup.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.Cliente;


@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long>{
	
	    Page<Cliente> findAllByNome(String nome, Pageable pageable);

	    Optional<Cliente> findByCpf(String CPF);

	    Optional<Cliente> findByEmail(String email);
}
