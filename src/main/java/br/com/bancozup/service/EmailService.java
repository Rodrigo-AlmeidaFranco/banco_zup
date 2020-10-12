package br.com.bancozup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.bancozup.mailer.Mail;
import br.com.bancozup.model.Cliente;
import br.com.bancozup.model.Conta;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Value("${mailer.sender}")
	private String remetente;

	public void emailAceiteConta(Cliente cliente, Conta conta ) {

		Mail mail = new Mail();
		mail.setTo(cliente.getEmail());
		mail.setFrom(remetente);
		
		mail.setContent("Seja Bem Vindo. \n" + cliente.getNome());
		mail.setContent("Conta:          \n" + conta.getConta());
		mail.setContent("Cod Conta:      \n" + conta.getCodConta());
		mail.setContent("Agencia:        \n" + conta.getAgencia());
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		message.setTo(mail.getTo());
		message.setFrom(mail.getFrom());

		emailSender.send(message);
	}

	public void emailRecusaConta(Cliente cliente ) {

		Mail mail = new Mail();
		mail.setTo(cliente.getEmail());
		mail.setFrom(remetente);

		mail.setSubject("tem Certeza " + cliente.getNome() + " ?");
		mail.setContent("Reconsidere por favor.");

		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		message.setTo(mail.getTo());
		message.setFrom(mail.getFrom());

		emailSender.send(message);
	}

}
