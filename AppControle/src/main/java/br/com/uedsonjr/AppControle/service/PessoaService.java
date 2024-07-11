package br.com.uedsonjr.AppControle.service;

import br.com.uedsonjr.AppControle.dto.PessoaDTO;
import br.com.uedsonjr.AppControle.model.Pessoa;
import br.com.uedsonjr.AppControle.repository.PessoaRepository;
import br.com.uedsonjr.AppControle.interfaces.PessoaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService implements PessoaInterface {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    @Transactional
    public Pessoa save(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da pessoa não pode estar vazio");
        }

        try {        	
            return pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar pessoa: " + e.getMessage());
        }
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    @Transactional
    public Pessoa update(Long id, Pessoa pessoa) {
        Optional<Pessoa> findPessoa = pessoaRepository.findById(id);

        if (findPessoa.isPresent()) {
            Pessoa existingPessoa = findPessoa.get();
            existingPessoa.setNome(pessoa.getNome());
            existingPessoa.setEndereco(pessoa.getEndereco());
            existingPessoa.setUf(pessoa.getUf());
            existingPessoa.setCidade(pessoa.getCidade());
            existingPessoa.setCep(pessoa.getCep());

            return pessoaRepository.save(existingPessoa);
        }

        return pessoaRepository.save(pessoa);
    }

    public PessoaDTO findPessoaMalaDireta(Long id) {
        Pessoa findPessoa = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com ID: " + id));

        String malaDireta = findPessoa.getEndereco() +
        					findPessoa.getUf() +
        					findPessoa.getCep() + 
        					findPessoa.getCidade(); 


        return new PessoaDTO(findPessoa.getId(), 
        					 findPessoa.getNome(), 
        					 malaDireta);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
        
        
    }
}
