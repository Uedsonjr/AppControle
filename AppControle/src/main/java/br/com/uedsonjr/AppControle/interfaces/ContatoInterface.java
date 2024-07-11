package br.com.uedsonjr.AppControle.interfaces;

import br.com.uedsonjr.AppControle.model.Contato;

import java.util.List;
import java.util.Optional;

public interface ContatoInterface {

    Contato save(Contato contato);
    Optional<Contato> findById(Long id);

    List<Contato> findAll(Long id);
    Contato update(Long id, Contato contato);

    void delete(Long id);
}