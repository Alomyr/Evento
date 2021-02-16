package ifrn.pi.eventos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
	
	@GetMapping
	public ModelAndView listar() {
		
		java.util.List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos",eventos);
		return mv;
	}	
	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {

		ModelAndView md = new ModelAndView();
		java.util.Optional<Evento> opt = er.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		md.setViewName("eventos/detalhes");
		Evento evento = opt.get();
		md.addObject("evento", evento);
		
		return md;
	}
}
