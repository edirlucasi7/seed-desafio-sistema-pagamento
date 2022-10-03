package com.deveficiente.sistemadepagamento.processandopagamento;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FormaPagamentoOfflineValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoPedidoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NovoPedidoRequest request = (NovoPedidoRequest) target;
        if(!request.isOffline()) {
            errors.rejectValue("tipoDePagamento", null, "A forma de pagamento precisa ser offline!");
        }
    }
}
