package com.deveficiente.sistemadepagamento.processandopagamento;

import com.deveficiente.sistemadepagamento.listagemformaspagamento.RegraFraude;
import com.deveficiente.sistemadepagamento.novorestaurante.Restaurante;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Component
public class VerificaMathPagamentosUsuarioRestauranteValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private Collection<RegraFraude> regraFraudes;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoSolicitationRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }
        NovoSolicitationRequest request = (NovoSolicitationRequest) target;
        Usuario usuario = manager.find(Usuario.class, request.getIdUser());
        Restaurante restaurante = manager.find(Restaurante.class, request.getIdRestaurant());

        boolean permitePagamento = usuario.permitePagamento(restaurante, request.getType(), regraFraudes);
        if(!permitePagamento) {
            errors.reject(null, "A forma, de pagamento entre usuário e restaurante não eh valida    ");
        }
    }
}
