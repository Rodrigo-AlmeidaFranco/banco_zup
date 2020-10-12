package br.com.bancozup.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancozup.model.Conta;
import br.com.bancozup.repository.ClienteRepo;
import br.com.bancozup.repository.ContaRepo;

@RestController
@RequestMapping(value="conta/contaBanco")
public class ContaController {
	
	@Autowired
	ContaRepo contaService;
	
	@Autowired
	ClienteRepo clienteRepo;
	
	@PostMapping("/criar")
	public Conta salvaConta(@RequestBody @Valid Conta conta) {
		return contaService.save(conta);
	}
	
	@GetMapping("/{id}")
	public Optional<Conta> getConta( @PathVariable Long id ) {
		return contaService.findById(id);
	}
	
	@GetMapping("/Cliente/{id}")
	public Conta getContabyCliente( @PathVariable Long id ) {
		return contaService.findContaByCliente( clienteRepo.getOne(id));
	}
	
	@PutMapping("/{id}")
	public Conta updateConta( @RequestBody @Valid Conta conta ) {
		return contaService.save( conta );
	}
	
	@DeleteMapping("/{id}")
	public void deleteConta( @PathVariable Long id ) {
		contaService.deleteById(id);
	}
}
