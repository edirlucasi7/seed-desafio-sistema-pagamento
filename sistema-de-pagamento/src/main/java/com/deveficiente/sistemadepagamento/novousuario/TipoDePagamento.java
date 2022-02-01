package com.deveficiente.sistemadepagamento.novousuario;

public enum TipoDePagamento {

	Visa(true, "Cartão visa"),
	Elo(true, "Cartão elo"),
	Master(true, "Cartão master"),
	Hipercard(true, "Cartão Hipercard"),
	Dinheiro(false, "Dinheiro"),
	Maquina(false, "Maquina"),
	Cheque(false, "Cheque");
	
	private boolean online;
	private String descricao;
	
	TipoDePagamento(boolean online, String descricao) {
		this.online = online;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
