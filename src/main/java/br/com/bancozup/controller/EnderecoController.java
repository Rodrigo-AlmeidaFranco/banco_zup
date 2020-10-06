package br.com.bancozup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancozup.model.Endereco;
import br.com.bancozup.service.EnderecoService;


@RestController
@RequestMapping(value="/conta/Endereco")
public class EnderecoController {
	
	@Autowired
    EnderecoService enderecoService;
	
	 @GetMapping
	    public Page<Endereco> busqueTodosEnderecos(@PageableDefault(size = 5) Pageable pageable) {
	        return enderecoService.busqueTodosClientes(pageable);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Endereco> busqueEnderecoPeloId(@PathVariable Long id) {
	        Endereco enderecoEncontradoPeloId = enderecoService.busqueEnderecoPeloId(id);
	        return ResponseEntity.ok(enderecoEncontradoPeloId);
	    }

	    @GetMapping("/{cep}")
	    public ResponseEntity<Endereco> busqueEnderecoPeloCep(@PathVariable String cep) {
	        Endereco enderecoEncontradoPeloCep = enderecoService.busqueEnderecoPeloCep(cep);
	        return ResponseEntity.ok(enderecoEncontradoPeloCep);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Endereco> atualizeEnderecoPeloId(@PathVariable Long id, @RequestBody @Valid Endereco endereco) {
	        Endereco enderecoAtualizado = enderecoService.atualizeEnderecoSeExistir(id, endereco);
	        return ResponseEntity.ok(enderecoAtualizado);
	    }

	    @DeleteMapping("/{id}")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteEnderecoPeloId(@PathVariable Long id) {
	        enderecoService.deleteEnderecoPeloId(id);
	    }
}
