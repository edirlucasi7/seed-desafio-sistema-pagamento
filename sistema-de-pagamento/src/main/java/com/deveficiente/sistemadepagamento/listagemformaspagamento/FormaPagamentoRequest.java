package com.deveficiente.sistemadepagamento.listagemformaspagamento;

import com.deveficiente.sistemadepagamento.compartilhado.ExistsId;
import com.deveficiente.sistemadepagamento.novorestaurante.Restaurante;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;

public class FormaPagamentoRequest {

	@ExistsId(domainClass = Usuario.class, fieldName = "id")
	private Long idUsuario;
	
	@ExistsId(domainClass = Restaurante.class, fieldName = "id")
	private Long idRestaurante;
	
	public FormaPagamentoRequest(Long idUsuario, Long idRestaurante) {
		this.idUsuario = idUsuario;
		this.idRestaurante = idRestaurante;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public Long getIdRestaurante() {
		return idRestaurante;
	}
	
}
