package br.com.bancozup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.TokenDeVerificacao;
import br.com.bancozup.model.User;

@Repository
public interface TokenDeVerificacaoRepo  extends JpaRepository< TokenDeVerificacao , Long>{
	
	TokenDeVerificacao findByToken(String token);
	 
	TokenDeVerificacao findByUser(User user);
}
