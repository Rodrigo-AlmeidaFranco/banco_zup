package br.com.bancozup.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bancozup.model.Cliente;
import br.com.bancozup.model.Endereco;


@Service
public interface ClienteService {
	
	Page<Cliente> busqueTodosClientes(Pageable pageable);
	
	List<Cliente> findAll( );
	
	Cliente atualizeClientePeloId(Long id, Cliente cliente);

    Cliente busqueClientePeloId(Long id);

    Cliente busqueClientePeloCPF(String cpf);

    Page<Cliente> busqueClientePeloNome(String name, Pageable pageable);

    Cliente salveNovoCliente(Cliente cliente);

    void deleteClientePeloId(Long id);

    void salveEnderecoCliente(Cliente cliente, Endereco endereco);


}
