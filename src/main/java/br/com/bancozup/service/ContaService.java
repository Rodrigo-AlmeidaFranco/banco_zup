package br.com.bancozup.service;

import org.springframework.stereotype.Service;

import br.com.bancozup.model.Conta;

@Service
public interface ContaService {

	Conta atualizeSaldoPeloId(Long id, Conta conta);

	Conta busqueContaPeloId(Long id);

	Conta salveNovoConta(Conta conta);

    void deleteContaPeloId(Long id);

}
