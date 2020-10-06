package br.com.bancozup.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bancozup.model.Transferencia;

@Service
public interface TransferenciaService {
	
	Page<Transferencia> busqueTodasTransferencias(Pageable pageable);

	Transferencia atualizeTransferenciaPeloId(Long id, Transferencia transferencia);

	Transferencia busqueTransferenciaPeloId(Long id);

	Transferencia salveNovaTransferencia(Transferencia transferencia);


}
