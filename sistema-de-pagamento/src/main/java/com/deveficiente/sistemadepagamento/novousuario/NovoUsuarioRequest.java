package com.deveficiente.sistemadepagamento.novousuario;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class NovoUsuarioRequest {

	@NotNull
	@Email
	private String email;
	
	@NotNull
	private Set<TipoDePagamento> formasPagamento = new HashSet<>();

	public NovoUsuarioRequest(@NotNull @Email String email, Set<TipoDePagamento> formasPagamento) {
		this.email = email;
		this.formasPagamento = formasPagamento;
	}
	
	public Usuario toModel() {
		return new Usuario(email, formasPagamento);
	}

	public String getEmail() {
		return email;
	}

	public Set<TipoDePagamento> getFormasPagamento() {
		return formasPagamento;
	}

}
