package com.deveficiente.sistemadepagamento.novousuario;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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

	public Set<TipoDePagamento> getFormasPagamento() {
		return formasPagamento;
	}

}
