package com.deveficiente.sistemadepagamento.listagemformaspagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

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
	
	@GetMapping("/formas-pagamentos")
	public ResponseEntity<List<FormasPagamentoVo>> listar(@Valid @RequestBody FormaPagamentoRequest request) {
				
		Usuario usuario = manager.find(Usuario.class, request.getIdUsuario());
		Restaurante restaurante = manager.find(Restaurante.class, request.getIdRestaurante());
		
		List<TipoDePagamento> opcoesPagamento = getMatchPagamentos(usuario, restaurante);
		
		return ResponseEntity.ok(opcoesPagamento.stream().map(FormasPagamentoVo::new).collect(Collectors.toList()));
		
	}

	private List<TipoDePagamento> getMatchPagamentos(Usuario usuario, Restaurante restaurante) {
		List<TipoDePagamento> pagamentosDisponiveis = new ArrayList<>(usuario.getFormasPagamento());
		pagamentosDisponiveis.retainAll(restaurante.getFormasPagamentoDisponiveis());
		return pagamentosDisponiveis;
	}
	
}
