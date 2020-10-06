package br.com.bancozup.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bancozup.model.Cliente;
import br.com.bancozup.service.ClienteService;

@RestController
@RequestMapping(value = "/conta/user")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public Page<Cliente> busqueTodosClientes(@PageableDefault(size = 5) Pageable pageable) {

		return clienteService.busqueTodosClientes(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> busqueClientePeloId(@PathVariable Long id) {
		Cliente clienteEncontradoPeloId = clienteService.busqueClientePeloId(id);
		return ResponseEntity.ok(clienteEncontradoPeloId);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<Page<Cliente>> busqueClientePeloNome(@PathVariable String name,
			@PageableDefault(size = 5) Pageable pageable) {
		return new ResponseEntity<>(clienteService.busqueClientePeloNome(name, pageable), HttpStatus.OK);
	}
	
	@GetMapping("/produtos")
	public List<Cliente> listaProdutos(){
		return clienteService.findAll();
	}

	@PostMapping
	public ResponseEntity<Cliente> salveCliente(@RequestBody @Valid Cliente client) {
		Cliente clienteAtualizado = clienteService.salveNovoCliente(client);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clienteAtualizado.getId()).toUri();
		return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
	}

	@PutMapping("/{id}")
	public Cliente atualizeClientePeloId(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		Cliente clienteEncontrado = clienteService.atualizeClientePeloId(id, cliente);
		return clienteEncontrado;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteClientePeloId(@PathVariable Long id) {
		clienteService.deleteClientePeloId(id);
	}
}
