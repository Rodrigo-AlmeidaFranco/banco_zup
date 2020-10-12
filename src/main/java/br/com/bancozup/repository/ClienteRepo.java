package br.com.bancozup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.Cliente;


@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Long>{
	
	    List<Cliente> findAllByNome(String nome );
	    
	    List<Cliente> findAll( );
	    
	    Optional<Cliente> findByCpf(String CPF);

	    Optional<Cliente> findByEmail(String email);
}
