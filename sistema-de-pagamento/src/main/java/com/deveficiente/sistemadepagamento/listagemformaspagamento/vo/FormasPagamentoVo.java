package com.deveficiente.sistemadepagamento.listagemformaspagamento.vo;

import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;

public class FormasPagamentoVo {
	
	private String id; 

	private String descricao;

	public FormasPagamentoVo(TipoDePagamento tipoPagamento) {
		this.id = tipoPagamento.name();
		this.descricao = tipoPagamento.getDescricao();
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
