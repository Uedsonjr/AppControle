package br.com.uedsonjr.AppControle.interfaces;

import br.com.uedsonjr.AppControle.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaInterface {

    Pessoa save(Pessoa pessoa);
    Optional<Pessoa> findById(Long id);

    List<Pessoa> findAll();
    Pessoa update(Long id, Pessoa pessoa);

    void delete(Long id);
}
