package br.com.bancozup.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	
	@NotEmpty(message = " O nome do Cliente é obrigatório")
	@Column(nullable = false)
	private String nome;
	
	@NotEmpty(message = " O Sobrenome do Cliente é obrigatório")
	@Column(nullable = false)
	private String sobrenome;
	
	@Email
	@Column(nullable = false)
	private String email;
	
	@CPF
	@Column(nullable = false)
	private String cpf;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date   dataNascimento;
	
	@OneToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

}