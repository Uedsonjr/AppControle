package br.com.uedsonjr.AppControle.model;

import jakarta.persistence.*;
import java.util.stream.Collectors;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "table_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String endereco;

    @Column
    private String uf;
    
    @Column
    private String cidade;
    
    @Column
    private String cep;

    @OneToMany
    @JoinColumn(name="id")
    private List<Contato> contatos;

    public Pessoa(Long id, String nome, String endereco, String uf, String cidade, String cep) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.uf = uf;
        this.cidade = cidade;
        this.cep = cep;
    }

    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) 
        	
        return true;
        
        if (o == null || getClass() != o.getClass()) 
        	
        return false;
        
        Pessoa pessoa = (Pessoa) o;
        
        return Objects.equals(id, pessoa.id);
    }

  
    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", uf='" + uf + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep='" + cep + '\'' + '}';
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public List<Contato> ExistsContact(Long id) { 
    	var contato = this.contatos.stream().filter(c -> c.getId() == id).collect(Collectors.toList());
    	if (contato.contains(id)) { 
    		return contato;
    	}
    	List<Contato> c = new ArrayList<Contato>();
    	return c;
    }

}
