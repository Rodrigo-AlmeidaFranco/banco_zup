package br.com.bancozup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.Transferencia;

@Repository
public interface TransferenciaRepo extends JpaRepository<Transferencia, Long>{
}
