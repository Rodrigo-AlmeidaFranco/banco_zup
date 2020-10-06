package br.com.bancozup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancozup.service.PropostaService;

@RestController
@RequestMapping(value="/conta/Proposta")
public class PropostaController {
	
	@Autowired
    PropostaService propostaeService;
	
}
