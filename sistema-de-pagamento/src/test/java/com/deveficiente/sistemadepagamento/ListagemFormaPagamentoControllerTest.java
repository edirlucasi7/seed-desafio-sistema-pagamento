package com.deveficiente.sistemadepagamento;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ListagemFormaPagamentoControllerTest {

	@Autowired
	private CustomMockMvc mvc;
	
	@Test
	@DisplayName("exibe opcoes de pagamento para o usuario baseado nas suas possibilidades de pagamento")
	void teste1() throws Exception {
		String email = "icety@gmail.com";
		Set<TipoDePagamento> formasPagamentoUsuario = Set.of(TipoDePagamento.Dinheiro, TipoDePagamento.Cheque);
		
		mvc.post("/usuarios", Map.of("email", email, "formasPagamento", formasPagamentoUsuario));
		
		String nome = "Nori Sushi";
		Set<TipoDePagamento> formasPagamentoRestaurante = Set.of(TipoDePagamento.Dinheiro, TipoDePagamento.Cheque);
		
		mvc.post("/restaurantes", Map.of("nome", nome, "formasPagamentoDisponiveis", formasPagamentoRestaurante));
		
		ResultActions resultado = mvc.getWithBody("/formas-pagamentos", Map.of("idUsuario", 1l, "idRestaurante", 1l));
		
		List<Map<String, Object>> opcoesPagamento = List.of(Map.of("id", TipoDePagamento.Dinheiro.name(),
				"descricao", TipoDePagamento.Dinheiro.getDescricao()), Map.of("id", TipoDePagamento.Cheque.name(),
						"descricao", TipoDePagamento.Cheque.getDescricao()));
		
		String jsonEsperado = new ObjectMapper()
				.writeValueAsString(opcoesPagamento);
		
		resultado.andExpect(MockMvcResultMatchers.content().json(jsonEsperado));
	}
	
	@Test
	@DisplayName("fluxo de listagem de pagamentos sem opcoes de pagamento para o restaurante")
	void teste2() throws Exception {
		String email = "icety@gmail.com";
		Set<TipoDePagamento> formasPagamentoUsuario = Set.of(TipoDePagamento.Dinheiro, TipoDePagamento.Cheque);
		
		mvc.post("/usuarios", Map.of("email", email, "formasPagamento", formasPagamentoUsuario));
		
		String nome = "Nori Sushi";
		Set<TipoDePagamento> formasPagamentoRestaurante = Set.of(TipoDePagamento.Visa, TipoDePagamento.Elo);
		
		mvc.post("/restaurantes", Map.of("nome", nome, "formasPagamentoDisponiveis", formasPagamentoRestaurante));
		
		ResultActions resultado = mvc.getWithBody("/formas-pagamentos", Map.of("idUsuario", 1l, "idRestaurante", 1l));
		
		List<Map<String, Object>> opcoesPagamento = List.of(Map.of(), Map.of());
		
		String jsonEsperado = new ObjectMapper()
				.writeValueAsString(opcoesPagamento);
		
		resultado.andExpect(MockMvcResultMatchers.content().json(jsonEsperado));
	}
}
