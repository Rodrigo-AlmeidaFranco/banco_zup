package br.com.bancozup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancozup.model.User;

@Repository
public interface UserRepo extends JpaRepository< User , Long>{
		
	User findByEmail( String email );
}
