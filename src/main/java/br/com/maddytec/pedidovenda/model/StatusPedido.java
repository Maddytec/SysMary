package br.com.maddytec.pedidovenda.model;

public enum StatusPedido{
	
	ORCAMENTO("Orçamento"),
	EMITIDO("Emitido"),
	CANCELADO("Cancelado"),
	BAIXADO("Baixado");
	
	private String nome;
	
	StatusPedido(String nome) {
	this.nome = nome;
	}

	public String getDescricao() {
		return nome;
	}
}
