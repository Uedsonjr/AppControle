package br.com.uedsonjr.AppControle.model;

public enum TipoContato {
	
	TELEFONE,
	CELULAR;
	
	public static int valorContato(TipoContato valorDoContato) {
		switch (valorDoContato) {
			case TipoContato.TELEFONE: 
				return 0;
			case TipoContato.CELULAR: 
				return 1;				
			default:
				throw new IllegalArgumentException("O valor está errado: " + valorDoContato);
		}
		
	}
	
}