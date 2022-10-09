package com.deveficiente.sistemadepagamento.processandopagamento;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
public class ExecutationTransactional {

    @Transactional
    public <T> T execute(Supplier<T> supplier) {
        return supplier.get();
    }
}
