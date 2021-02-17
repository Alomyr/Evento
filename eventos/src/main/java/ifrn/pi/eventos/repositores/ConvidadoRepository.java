package ifrn.pi.eventos.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.eventos.models.Convidados;
import ifrn.pi.eventos.models.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidados, Long>{
	
	List<Convidados> findByEvento(Evento evento);
	
}
