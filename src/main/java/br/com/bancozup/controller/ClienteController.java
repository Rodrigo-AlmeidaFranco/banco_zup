package br.com.bancozup.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancozup.model.Cliente;
import br.com.bancozup.repository.ClienteRepo;

@RestController
@RequestMapping(value="/user")
public class ClienteController {

	@Autowired
	ClienteRepo clienteService;
	
	@GetMapping("/{id}")
	public Optional<Cliente> getCliente( @PathVariable Long id ) {
		return clienteService.findById(id);
	}
	
	@PutMapping("/{id}")
	public Cliente updateCliente( @RequestBody @Valid Cliente cliente ) {
		return clienteService.save(cliente);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCliente( @PathVariable Long id ) {
		 clienteService.deleteById(id);
	}
	
}
