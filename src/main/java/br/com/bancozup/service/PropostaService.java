package br.com.bancozup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bancozup.model.Proposta;

@Service
public interface PropostaService {
	
	Page<Proposta> busqueTodosClientes(Pageable pageable);

	Proposta busquePropostaPeloId(Long id);

	Proposta busquePropostaPeloCliente(Long id_cliente);

	Proposta salveNovaProposta(Proposta proposta);

}
