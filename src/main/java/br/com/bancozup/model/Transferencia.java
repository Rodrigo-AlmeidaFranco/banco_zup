package br.com.bancozup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Transferencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	
	private double valorTransferencia;
	
	@OneToOne
	@JoinColumn(name = "contaRemetente_id")
	private Conta contaRemetente;
	
	@OneToOne
	@JoinColumn(name = "contaDestinataria_id")
	private Conta contaDestinataria;

	public Transferencia(double valorTransferencia, Conta contaRemetente, Conta contaDestinataria) {
		super();
		this.valorTransferencia = valorTransferencia;
		this.contaRemetente = contaRemetente;
		this.contaDestinataria = contaDestinataria;
	}
	
	
	
}
