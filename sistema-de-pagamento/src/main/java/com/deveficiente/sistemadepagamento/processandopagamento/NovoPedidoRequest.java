package com.deveficiente.sistemadepagamento.processandopagamento;

import com.deveficiente.sistemadepagamento.compartilhado.ExistsId;
import com.deveficiente.sistemadepagamento.novorestaurante.Restaurante;
import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;

import javax.validation.constraints.NotNull;

public class NovoPedidoRequest {

    @NotNull
    private TipoDePagamento tipoDePagamento;
    @NotNull
    @ExistsId(domainClass = Restaurante.class, fieldName = "id")
    private Long idRestaurante;
    @NotNull
    @ExistsId(domainClass = Usuario.class, fieldName = "id")
    private Long idUsario;

    public TipoDePagamento getTipoDePagamento() {
        return tipoDePagamento;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public Long getIdUsario() {
        return idUsario;
    }

    public boolean isOffline() {
        return !tipoDePagamento.isOnline();
    }
}
