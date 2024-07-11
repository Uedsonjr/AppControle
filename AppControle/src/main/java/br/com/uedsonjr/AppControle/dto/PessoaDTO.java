package br.com.uedsonjr.AppControle.dto;

//Definindo uma classe imutável para armazenamento de dados.
public record PessoaDTO(
		Long id,
		String nome, 
		String malaDireta) {
	
}
