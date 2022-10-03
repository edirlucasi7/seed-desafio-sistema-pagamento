package com.deveficiente.sistemadepagamento.processandopagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@RestController
public class PagamentoOfflineController {

    @Autowired
    private VerificaMathPagamentosUsuarioRestauranteValidator verificaMathPagamentosUsuarioRestauranteValidator;

    @InitBinder("novoPedidoRequest")
    public void init(WebDataBinder binder) {
        binder.addValidators(new FormaPagamentoOfflineValidator(),  verificaMathPagamentosUsuarioRestauranteValidator);
    }

    @PostMapping("/pagamento/offline/{idPedido}")
    public String processa(@PathVariable Long idPedido, @RequestBody @Valid NovoPedidoRequest request) throws BindException {

        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> pedido = restTemplate.getForObject("http://localhost:8080/api/pedidos/{idPedido}", Map.class, idPedido);
            System.out.println(pedido);
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                BindException bindException = new BindException("", "");
                bindException.reject("", "Pedido informado nao existe!");
                throw bindException;
            }
        }
        return "deu certo";
    }

    @GetMapping("/api/pedidos/{idPedido}")
    public Map<String, Object> buscaPedido(Long idPedido) {
        return Map.of("valor", new BigDecimal("50.00"));
    }
}