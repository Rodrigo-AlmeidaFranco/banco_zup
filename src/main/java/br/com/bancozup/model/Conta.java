package br.com.bancozup.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.util.Random;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	private String agencia;
	private String conta;
	private String codConta;
	private double  saldo;
	
	 @OneToOne
	 @JoinColumn(name = "cliente_id")
	 private Cliente cliente;

	public Conta( Cliente cliente  ) {
		super();
		
		Random rand = new Random();
		
		this.setConta   ( String.valueOf(rand.nextInt( 99999999 )));
		this.setCodConta( String.valueOf(rand.nextInt( 999      )));
		this.setAgencia ( String.valueOf(rand.nextInt( 9999     )));
		this.setCliente ( cliente                                 );
		this.setSaldo   ( 0.00 );
	}
	 
	 
	 
}
