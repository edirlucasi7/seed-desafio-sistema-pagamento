package com.deveficiente.sistemadepagamento.novorestaurante;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantesController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/restaurantes")
	@Transactional
	public void cadastrar(@Valid @RequestBody NovoRestauranteRequest request) {
		Restaurante novoRestaurante = request.toModel();
		manager.persist(novoRestaurante);
	}

	
}
