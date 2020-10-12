package br.com.bancozup.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancozup.model.Conta;
import br.com.bancozup.model.Transferencia;
import br.com.bancozup.repository.ContaRepo;
import br.com.bancozup.repository.TransferenciaRepo;

@RestController
@RequestMapping(value="transferencia")
public class TransferenciaController implements Runnable{
	
	@Autowired
	TransferenciaRepo transfRepo;
	
	@Autowired
	ContaRepo  contaRepo;
	
	@PostMapping("/{id}")
	private void transferencia(@RequestHeader("contaOrigem") Conta contaOrigem, @RequestBody Double valor,
			@PathVariable("id") Long id) {
		new Thread() {

			@Override
			public void run() {

				Conta contaDestino = contaRepo.getOne(id);
				if( contaDestino!= null )
				{	
					transferir(valor, contaOrigem, contaDestino);
					contaRepo.save(contaDestino);
				}
			}
		}.start();
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity transferir( Double valor, Conta contaOrigem, Conta contaDestino ) {
		Transferencia transferencia = new Transferencia(valor, contaOrigem, contaDestino);
		contaDestino.setSaldo(contaDestino.getSaldo() + valor);
		transfRepo.save(transferencia);
		return ResponseEntity.status(CREATED).body(transferencia);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
