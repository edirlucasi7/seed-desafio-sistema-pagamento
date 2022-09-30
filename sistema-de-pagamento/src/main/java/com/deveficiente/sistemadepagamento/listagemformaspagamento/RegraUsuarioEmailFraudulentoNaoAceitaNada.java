package com.deveficiente.sistemadepagamento.listagemformaspagamento;

import com.deveficiente.sistemadepagamento.novousuario.TipoDePagamento;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class RegraUsuarioEmailFraudulentoNaoAceitaNada implements RegraFraude {

    public boolean aceita(TipoDePagamento formasPagamento, Usuario usuario) {
        return false;
    }
}
