package br.com.bancozup.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bancozup.arquivo.Disco;
import br.com.bancozup.model.Arquivo;
import br.com.bancozup.model.Cliente;
import br.com.bancozup.model.Conta;
import br.com.bancozup.model.Endereco;
import br.com.bancozup.model.Proposta;
import br.com.bancozup.repository.ArquivoRepo;
import br.com.bancozup.repository.ClienteRepo;
import br.com.bancozup.repository.ContaRepo;
import br.com.bancozup.repository.EnderecoRepo;
import br.com.bancozup.repository.PropostaRepo;
import br.com.bancozup.service.EmailService;

@RestController
@RequestMapping(value="cadastro/user")
public class CadastroController {
	
	@Autowired
	ClienteRepo clienteRepo;
	
	@Autowired
	EnderecoRepo enderecoRepo;
	
	@Autowired
	ArquivoRepo arquivoRepo;
	
	@Autowired
	ContaRepo contaRepo;
	
	@Autowired
	PropostaRepo propostaRepo;
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	private Disco disco;
	
	@PostMapping
	public void upload(@RequestParam MultipartFile fotoFrente, @RequestParam MultipartFile fotoVerso ) {
		disco.salvarFoto(fotoFrente);
		disco.salvarFoto(fotoVerso );
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/criar")
	public ResponseEntity salvaCliente(@RequestBody @Valid Cliente cliente) {
		cliente =  clienteRepo.save(cliente);
		URI location = nextLocation( cliente.getId(), "/{id}/endereco");
		return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).header("clientePrenchido", Boolean.TRUE.toString()).body(cliente);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("criar/{id}/endereco")
	public ResponseEntity salvaEndereco(@RequestHeader("clientePrenchido") Boolean passoAnteriorOk, @RequestBody @Valid Endereco endereco, @PathVariable("id") Long id) {
		if( passoAnteriorOk != null && !passoAnteriorOk )
			return ResponseEntity.status(UNPROCESSABLE_ENTITY).body("Completar passos anteriores");
		
		endereco =  enderecoRepo.save(endereco);
		URI location = nextLocation( id, "/arquivo");
		return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).header("enderecoPreenchido", Boolean.TRUE.toString()).body(endereco);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("criar/{id}/endereco/arquivo")
	public ResponseEntity salvaArquivo( @RequestHeader("clientePrenchido") Boolean passoAnteriorOk,
										@RequestParam("frente") MultipartFile Documentofrente,
            							@RequestParam("verso")  MultipartFile DocumentoVerso,
            							@PathVariable("id") Long id)
										{
		if( passoAnteriorOk != null && !passoAnteriorOk )
			return ResponseEntity.status(UNPROCESSABLE_ENTITY).body("Completar passos anteriores");
		Arquivo arq = new Arquivo();
		arq.setFrente(Documentofrente.getName());
		arq.setVerso(Documentofrente.getName());
		arq.setCliente( clienteRepo.getOne(id));
		disco.salvarFoto(Documentofrente);
		disco.salvarFoto(DocumentoVerso );
		arq = arquivoRepo.save( arq );
		URI location = nextLocation( id, "/proposta");
		return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).header("arquivoEnviado", Boolean.TRUE.toString()).body(arq);
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("criar/{id}/endereco/arquivo/proposta")
	public ResponseEntity geraProposta(@RequestHeader("arquivoEnviado") Boolean passoAnteriorOk, @PathVariable("id") Long id ) {
		
		if( passoAnteriorOk != null && !passoAnteriorOk )
			return ResponseEntity.status(UNPROCESSABLE_ENTITY).body("Completar passos anteriores");
		
		Cliente  cliente  = clienteRepo.findById(id).get();
		Endereco endereco = enderecoRepo.findByCliente(cliente);
		Proposta proposta = new Proposta( cliente );
		
		propostaRepo.save( proposta );
		
		return ResponseEntity.status(OK).header("PropostaEnviada", Boolean.TRUE.toString()).header("idProposta", proposta.getId().toString()).body( cliente.toString()+ endereco.toString());
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("criar/{id}/endereco/arquivo/proposta/{ok}")
	public ResponseEntity aceiteProposta(@RequestHeader("arquivoEnviado") Boolean passoAnteriorOk,
			@RequestHeader("idProposta") String idProposta, @PathVariable("id") Long id, @PathVariable Boolean aceite ) {
		
		if( passoAnteriorOk != null && !passoAnteriorOk )
			return ResponseEntity.status(UNPROCESSABLE_ENTITY).body("Completar passos anteriores");
		
		Cliente  cliente  = clienteRepo.findById(id).get();
		
		if( aceite )
		{	
			Proposta proposta = propostaRepo.findById(Long.parseLong(idProposta)).get();
			Conta conta = new Conta( clienteRepo.findById(id).get( ));
			proposta.setStatus(true);
			propostaRepo.save(proposta);
			contaRepo.save( conta );
			
			emailService.emailAceiteConta( cliente, conta );
		}
		else
			emailService.emailRecusaConta(cliente );
		
		return ResponseEntity.status(CREATED).body( cliente );
	}
	
	 private URI nextLocation(Long id, String next ) {
	        Optional<Cliente> cliente = clienteRepo.findById(id);
	        return ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path( next )
	                .buildAndExpand(cliente.get().getId())
	                .toUri();
	    }
	 
}
