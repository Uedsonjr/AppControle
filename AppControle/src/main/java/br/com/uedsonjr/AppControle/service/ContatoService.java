package br.com.uedsonjr.AppControle.service;

import br.com.uedsonjr.AppControle.model.Contato;
import br.com.uedsonjr.AppControle.model.Pessoa;
import br.com.uedsonjr.AppControle.repository.ContatoRepository;
import br.com.uedsonjr.AppControle.repository.PessoaRepository;
import br.com.uedsonjr.AppControle.interfaces.ContatoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Optional;

@Service
public class ContatoService implements ContatoInterface {

    private final ContatoRepository contatoRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository, PessoaRepository pessoaRepository) {
        this.contatoRepository = contatoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    @Transactional
    public Contato save(Contato contato) {
    	contato.validate();

        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(contato.getId());

        Pessoa pessoa = pessoaOptional.orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada na base de dados, ID: " + contato.getId()));
        contato.setPessoa(pessoa);
        return contatoRepository.save(contato);
    }

    @Override
    public Optional<Contato> findById(Long id) {
        return contatoRepository.findById(id);
    }

    @Override
    public List<Contato> findAll(Long id) {
    	if (id == null)
    		throw new IllegalArgumentException("Id nao pode ser nulo");
    	
    	Pessoa pessoa = pessoaRepository.getReferenceById(id);
    	List<Contato> listOfContacts = pessoa.ExistsContact(id);
    	
        return listOfContacts;
    }

    @Override
    @Transactional
    public Contato update(Long id, Contato contato) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        Contato existingContato = contatoOptional.orElse(contato);
        existingContato.setContato(contato.getContato());
        existingContato.setTipoContato(contato.getTipoContato());

        return contatoRepository.save(existingContato);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        contatoRepository.deleteById(id);
    }
}
