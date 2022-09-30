package com.deveficiente.sistemadepagamento.listagemformaspagamento;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.sistemadepagamento.listagemformaspagamento.vo.FormasPagamentoVo;
import com.deveficiente.sistemadepagamento.novorestaurante.Restaurante;
import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;

@RestController
public class ListagemFormaPagamentoController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private Collection<RegraFraude> regrasFraude;

	@GetMapping("/formas-pagamentos")
	public ResponseEntity<List<FormasPagamentoVo>> listar(@Valid @RequestBody FormaPagamentoRequest request) {

		Usuario usuario = manager.find(Usuario.class, request.getIdUsuario());
		Restaurante restaurante = manager.find(Restaurante.class, request.getIdRestaurante());

		List<TipoDePagamento> opcoesPagamento = usuario.getMatchPagamentos(restaurante, regrasFraude);

		return ResponseEntity.ok(opcoesPagamento.stream().map(FormasPagamentoVo::new).collect(Collectors.toList()));

	}

}
