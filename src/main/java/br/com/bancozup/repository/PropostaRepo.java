package br.com.bancozup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.Proposta;

@Repository
public interface PropostaRepo extends JpaRepository<Proposta, Long>{
	
	Optional<Proposta> findById(Long id);
}
