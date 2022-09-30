package com.deveficiente.sistemadepagamento.novorestaurante;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deveficiente.sistemadepagamento.listagemformaspagamento.vo.FormasPagamentoVo;
import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;

@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<TipoDePagamento> formasPagamentoDisponiveis = new HashSet<>();
	
	public Restaurante() { }

	public Restaurante(@NotBlank String nome,
			@NotNull Set<TipoDePagamento> formasPagamentoDisponiveis) {
		this.nome = nome;
		this.formasPagamentoDisponiveis.addAll(formasPagamentoDisponiveis);
	}

	public String getNome() {
		return nome;

	}

	public Set<TipoDePagamento> getFormasPagamentoDisponiveis() {
		return formasPagamentoDisponiveis;
	}

	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nome=" + nome + ", formasPagamentoDisponiveis=" + formasPagamentoDisponiveis
				+ "]";
	}

    public boolean aceita(TipoDePagamento tipoDePagamento) {
		return this.formasPagamentoDisponiveis.contains(tipoDePagamento);
    }
}
