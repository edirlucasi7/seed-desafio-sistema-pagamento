package com.deveficiente.sistemadepagamento.novorestaurante;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;

public class NovoRestauranteRequest {

	@NotBlank
	private String nome;
	
	@NotNull
	private Set<TipoDePagamento> formasPagamentoDisponiveis = new HashSet<>();

	public NovoRestauranteRequest(@NotBlank String nome,
			@NotNull Set<TipoDePagamento> formasPagamentoDisponiveis) {
		this.nome = nome;
		this.formasPagamentoDisponiveis = formasPagamentoDisponiveis;
	}
	
	public Restaurante toModel() {
		return new Restaurante(nome, formasPagamentoDisponiveis);
	}

	public String getNome() {
		return nome;

	}

	public Set<TipoDePagamento> getFormasPagamentoDisponiveis() {
		return formasPagamentoDisponiveis;
	}
	
}
