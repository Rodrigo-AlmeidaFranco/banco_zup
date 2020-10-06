package br.com.bancozup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bancozup.model.Endereco;

@Service
public interface EnderecoService {
	
	Endereco salveNovoEndereco(Long id, Endereco endereco);

    Page<Endereco> busqueTodosClientes(Pageable pageable);

    Endereco busqueEnderecoPeloId(Long id);

    Endereco busqueEnderecoPeloCep(String cep);

    Endereco atualizeEnderecoSeExistir(Long id, Endereco enderecoDTO);

    void deleteEnderecoPeloId(Long id);
}
