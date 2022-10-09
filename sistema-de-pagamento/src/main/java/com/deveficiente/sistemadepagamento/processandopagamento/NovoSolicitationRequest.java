package com.deveficiente.sistemadepagamento.processandopagamento;

import com.deveficiente.sistemadepagamento.compartilhado.ExistsId;
import com.deveficiente.sistemadepagamento.novorestaurante.Restaurante;
import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

public class NovoSolicitationRequest {

    @NotNull
    private TipoDePagamento type;
    @NotNull
    @ExistsId(domainClass = Restaurante.class, fieldName = "id")
    private Long idRestaurant;
    @NotNull
    @ExistsId(domainClass = Usuario.class, fieldName = "id")
    private Long idUser;

    public TipoDePagamento getType() {
        return type;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public Long getIdUser() {
        return idUser;
    }

    public boolean isOffline() {
        return !type.isOnline();
    }

    public Transaction toModel(Long idSolicitation, Number value, EntityManager manager) {
        Usuario user = manager.find(Usuario.class, idSolicitation);
        Restaurante restaurant = manager.find(Restaurante.class, idRestaurant);
        return new Transaction(idSolicitation, value, user, restaurant, StatusSolicitation.IN_PROGRESS);
    }
}
