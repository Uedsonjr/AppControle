package br.com.uedsonjr.AppControle.resource;

import br.com.uedsonjr.AppControle.dto.PessoaDTO;
import br.com.uedsonjr.AppControle.model.Pessoa;
import br.com.uedsonjr.AppControle.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;


@Controller
@RestController
@RequestMapping("/api/pessoas")
public class PessoaResource {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Adiciona um novo registro de pessoa")
    
    @PostMapping
    public ResponseEntity<Pessoa> adicionarPessoa(
    		@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.save(pessoa);
        if (novaPessoa == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @Operation(summary = "Recupera os detalhes de uma pessoa específica pelo ID")
    
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(
    		@PathVariable("id") Long pessoaId) {
    	
        Optional<Pessoa> pessoaEncontrada = pessoaService.findById(pessoaId);
        
        return pessoaEncontrada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Recupera todos os registros de pessoas")
    
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodasPessoas() {
        List<Pessoa> pessoas = pessoaService.findAll();
        if (pessoas == null || pessoas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(pessoas);
    }

    @Operation(summary = "Atualiza as informações de uma pessoa existente com base no ID")
    
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(
    		@PathVariable("id") Long pessoaId, 
    		@RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.update(pessoaId, pessoa);
        if (pessoaAtualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @Operation(summary = "Remove um registro de pessoa pelo ID")
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPessoa(@PathVariable("id") Long pessoaId) {
    	pessoaService.delete(pessoaId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Recupera os detalhes de uma pessoa com a mala direta do seu endereço pelo ID")
    
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<PessoaDTO> obterPessoaComMalaDireta(
    		@PathVariable("id") Long pessoaId) {
        PessoaDTO pessoaDTO = pessoaService.findPessoaMalaDireta(pessoaId);
        if (pessoaDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(pessoaDTO);
    }
}
