package br.com.uedsonjr.AppControle.repository;

import br.com.uedsonjr.AppControle.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository
				<Contato, Long> {
	
}
