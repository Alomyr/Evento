package ifrn.pi.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.eventos.models.Evento;

@Controller
public class EventosController {
	@RequestMapping("/cadastro")
	public String form() {
		return "cadastro";
	}

	@PostMapping("/eventos")
	public String adicionar(Evento evento) {
		System.out.println(evento);
		return "evento-adicionado";
	}
}
