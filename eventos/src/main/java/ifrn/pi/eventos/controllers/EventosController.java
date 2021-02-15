package ifrn.pi.eventos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositores.EventosRepositorys;

@Controller
public class EventosController {
	
	@Autowired
	private EventosRepositorys er;
	
	@RequestMapping("/cadastro")
	public String form() {
		return "eventos/cadastro";
	}

	@PostMapping("/eventos")
	public String adicionar(Evento evento) {
		
		System.out.println(evento);
		er.save(evento);
		return "eventos/evento-adicionado";
	}
}
