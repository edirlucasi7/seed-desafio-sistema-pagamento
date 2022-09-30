package com.deveficiente.sistemadepagamento.novousuario;

import com.deveficiente.sistemadepagamento.listagemformaspagamento.RegraFraude;
import com.deveficiente.sistemadepagamento.listagemformaspagamento.RegraUsuarioEmailFraudulento;
import com.deveficiente.sistemadepagamento.novorestaurante.Restaurante;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import static java.util.stream.Collectors.*;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<TipoDePagamento> formasPagamento = new HashSet<>();
	
	public Usuario() { }
	
	public Usuario(@NotNull @Email String email, Set<TipoDePagamento> formasPagamento) {
		this.email = email;
		this.formasPagamento.addAll(formasPagamento);
	}

	public String getEmail() {
		return email;
	}

    public List<TipoDePagamento> getMatchPagamentos(Restaurante restaurante, Collection<RegraFraude> regrasFraude) {
		return this.formasPagamento.stream()
				.filter(restaurante::aceita)
				.filter(formasPagamento -> regrasFraude.stream().allMatch(
						regra -> regra.aceita(formasPagamento, this))).collect(toList());
    }
}
