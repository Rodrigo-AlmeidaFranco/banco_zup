package br.com.bancozup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.Arquivo;
import br.com.bancozup.model.Cliente;

@Repository
public interface ArquivoRepo extends JpaRepository<Arquivo, Long>{
	
	Arquivo findByCliente( Cliente cliente );
}
