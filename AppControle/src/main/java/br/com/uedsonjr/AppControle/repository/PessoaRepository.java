package br.com.uedsonjr.AppControle.repository;

import br.com.uedsonjr.AppControle.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository
				<Pessoa, Long> {

}
