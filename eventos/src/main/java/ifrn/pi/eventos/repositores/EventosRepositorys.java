package ifrn.pi.eventos.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.eventos.models.Evento;

public interface EventosRepositorys extends JpaRepository<Evento, Long>{
	
	
	
}
