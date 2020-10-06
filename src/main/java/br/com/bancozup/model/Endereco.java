package br.com.bancozup.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Endereco {
	
	 	@Id
	    @GeneratedValue
	    private Long id;
	 	
	 	@NotEmpty(message = "O CEP é obrigatório")
	    private String cep;
	 	
	 	@NotEmpty(message = "A Rua é obrigatória")
	    private String rua;
	 	
	 	@NotEmpty(message = "O Bairro é obrigatório")
	    private String bairro;
	 	
	 	@NotEmpty(message = "O Complemento é obrigatório")
	    private String complemento;
	 	
	 	@NotEmpty(message = "A Cidade é obrigatório")
	    private String cidade;
	 	
	 	@NotEmpty(message = "O Estado é obrigatório")
	    private String estado;
}
