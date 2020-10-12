package br.com.bancozup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancozup.model.Cliente;
import br.com.bancozup.model.Proposta;
import br.com.bancozup.repository.PropostaRepo;

@RestController
@RequestMapping(value="/conta/proposta")
public class PropostaController {
	
	@Autowired
	PropostaRepo propostaService;
	
	@PostMapping("/criar")
	public Proposta salvaProposta(@RequestBody @Valid Cliente cliente) {
		Proposta proposta = new Proposta( 0L, cliente, false );
		return propostaService.save( proposta );
	}
	
	@GetMapping("/propostas")
	public Page<Proposta> getPropostas(@PageableDefault(size = 5) Pageable pageable) {
		return propostaService.findAll(pageable);
	}
}
