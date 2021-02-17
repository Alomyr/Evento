package ifrn.pi.eventos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Convidados;
import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositores.ConvidadoRepository;
import ifrn.pi.eventos.repositores.EventosRepositorys;

@Controller
@RequestMapping("/eventos")
public class EventosController {

	@Autowired
	private EventosRepositorys er;
	@Autowired
	private ConvidadoRepository cr;

	@RequestMapping("/cadastro")
	public String form() {
		return "eventos/cadastro";
	}

	@PostMapping
	public String adicionar(Evento evento) {
		er.save(evento);
		return "eventos/evento-adicionado";
	}

	@GetMapping
	public ModelAndView listar() {

		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> opt = er.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		md.setViewName("eventos/detalhes");
		Evento evento = opt.get();
		md.addObject("evento", evento);
		
		
		List<Convidados> convidados = cr.findByEvento(evento);
		md.addObject("covidados", convidados);
		
		return md;
	}

	@PostMapping("/{idevento}")
	public String savaConvidado(@PathVariable Long idevento, Convidados convidados) {
		
		Optional<Evento> optc = er.findById(idevento);
		if (optc.isEmpty()) {
			return "redirect:/eventos";
		}
		Evento evento = optc.get();
		
		convidados.setEvento(evento);
		cr.save(convidados);
		
		return "redirect:/eventos/{idevento}";
	}
}
