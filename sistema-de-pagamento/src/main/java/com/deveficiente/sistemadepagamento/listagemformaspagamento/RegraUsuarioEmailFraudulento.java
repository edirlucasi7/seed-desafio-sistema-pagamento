package com.deveficiente.sistemadepagamento.listagemformaspagamento;

import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RegraUsuarioEmailFraudulento implements RegraFraude {

    private Set<String> emailsBloqueados = Set.of("seya@teste.com");

    public boolean aceita(TipoDePagamento formasPagamento, Usuario usuario) {
        if(!formasPagamento.online) {
            return true;
        }
        return formasPagamento.online && !emailsBloqueados.contains(usuario.getEmail());
    }
}
