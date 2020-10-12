package br.com.bancozup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.Cliente;
import br.com.bancozup.model.Endereco;

@Repository
public interface EnderecoRepo extends JpaRepository<Endereco, Long>{
	Optional<Endereco> findByCep(String cep);
	
	Endereco findByCliente( Cliente cliente );
}
