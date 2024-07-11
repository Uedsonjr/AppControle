package br.com.uedsonjr.AppControle.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "table_contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer tipoContato;

    @Column(nullable = false)
    private String contato;

    @ManyToOne
    private Pessoa pessoa;

    // Construtor com parâmetros
    public Contato(Long id, TipoContato tipoContato, String contato, Pessoa pessoaid) {
        this.id = id;
        this.tipoContato = TipoContato.valorContato(tipoContato); 
        this.contato = contato;
        this.pessoa = pessoaid;
    }

    // Construtor sem parâmetros
    public Contato() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(Integer tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    // Métodos equals e hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", tipoContato=" + tipoContato +
                ", contato='" + contato + '\'' + '}';
    }
    
    public void validate() {
    	if (this.contato == null) { 
    		throw new IllegalArgumentException("Contato nao pode ser nulo");
    	}
    	if (this.tipoContato == null) { 
    		throw new IllegalArgumentException("Contato nao pode ser nulo");
    	}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) 
        return true;
        if (o == null || getClass() != o.getClass()) 
        return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id);
    }

}
