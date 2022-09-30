package com.deveficiente.sistemadepagamento.listagemformaspagamento;

import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;

public interface RegraFraude {

    boolean aceita(TipoDePagamento tipoDePagamento, Usuario usuario);
}
