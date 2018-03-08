package br.com.maddytec.pedidovenda.model;

public enum TipoProduto {

	SIMPLES("Produto simples"),
	VARIAVEL("Produto variável");
	
	private String descricao;
	
	TipoProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}