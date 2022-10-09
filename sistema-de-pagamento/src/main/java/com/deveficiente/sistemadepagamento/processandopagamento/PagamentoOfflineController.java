package com.deveficiente.sistemadepagamento.processandopagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

@RestController
public class PagamentoOfflineController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private VerificaMathPagamentosUsuarioRestauranteValidator verificaMathPagamentosUsuarioRestauranteValidator;
    @Autowired
    private ExecutationTransactional execute;

    @InitBinder("novoPedidoRequest")
    public void init(WebDataBinder binder) {
        binder.addValidators(new FormaPagamentoOfflineValidator(),  verificaMathPagamentosUsuarioRestauranteValidator);
    }

    @PostMapping("/pagamento/offline/{idPedido}")
    public String execute(@PathVariable Long idPedido, @RequestBody @Valid NovoSolicitationRequest request) throws BindException {

        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> pedido = restTemplate.getForObject("http://localhost:8080/api/pedidos/{idPedido}", Map.class, idPedido);
            Number value = (Number) pedido.get("valor");

            Transaction transactionSaved = execute.execute(() -> {
                Transaction transactionalOffline = request.toModel(idPedido, value.doubleValue(), manager);
                manager.persist(transactionalOffline);
                return transactionalOffline;
            });
            return transactionSaved.getUuid();
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