package com.deveficiente.sistemadepagamento.novousuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuariosController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/usuarios")
	@Transactional
	public void cadastrar(@Valid @RequestBody NovoUsuarioRequest request) {
		Usuario novoUsuario = request.toModel();
		manager.persist(novoUsuario);
	}
	
}
