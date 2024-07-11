package br.com.uedsonjr.AppControle.resource;

import br.com.uedsonjr.AppControle.service.ContatoService;
import br.com.uedsonjr.AppControle.model.Contato;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/contatos")
public class ContatoResource {

	// final utilizado para manter a integridade dos dados do Contato 
    private final ContatoService contatoService;

    @Autowired
    public ContatoResource(ContatoService contatoService) {
        this.contatoService = contatoService;
    }
    
    @Operation(summary = "Adiciona um novo contato associado a uma pessoa já existente")
    
    @PostMapping
    public ResponseEntity<Contato> adicionarContato(
    		@RequestBody Contato contato) {
        Contato contatoCriado = contatoService.save(contato);
        if (contatoCriado == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoCriado);
    }

    @Operation(summary = "Recupera os detalhes de um contato específico pelo ID")
    
    @GetMapping("/{id}")
    public ResponseEntity<Contato> obterContatoPorId(
    		@PathVariable("id") Long contatoId) {
    	
        Optional<Contato> contatoEncontrado = contatoService.findById(contatoId);
        if (contatoEncontrado.isEmpty()) {
        	
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contatoEncontrado.get());
    }

    @Operation(summary = "Recupera todos os contatos associados a uma pessoa específica pelo ID da pessoa")
    
    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<Contato>> listarContatosPorPessoa(
    		@PathVariable("pessoaId") Long pessoaId) {
    	
        List<Contato> contatos = contatoService.findAll(pessoaId);
        if (contatos == null || contatos.isEmpty()) {
        	
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        return ResponseEntity.ok(contatos);
    }

    @Operation(summary = "Atualiza as informações de um contato existente com base no ID")
    
    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(
    		@PathVariable("id") Long contatoId, 
    		@RequestBody Contato contato) {
        Contato contatoAtualizado = contatoService.update(contatoId, contato);
        if (contatoAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contatoAtualizado);
    }

    @Operation(summary = "Remove um contato específico pelo ID")
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerContato(@PathVariable("id") Long contatoId) {
    	contatoService.delete(contatoId);
        return ResponseEntity.noContent().build();
    }
}
