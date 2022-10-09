package com.deveficiente.sistemadepagamento.processandopagamento;

import com.deveficiente.sistemadepagamento.novorestaurante.Restaurante;
import com.deveficiente.sistemadepagamento.novousuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private @NotNull Long idSolicitation;
    private @NotNull @Positive Number value;
    @ManyToOne
    private @NotNull Usuario user;
    @ManyToOne
    private @NotNull Restaurante restaurant;
    @Enumerated(STRING)
    private @NotNull StatusSolicitation status;

    @Deprecated
    public Transaction() {
    }

    public Transaction(@NotNull Long idSolicitation, @NotNull @Positive Number value, @NotNull @Valid Usuario user,
                       @NotNull @Valid Restaurante restaurant, StatusSolicitation inProgress) {
            this.idSolicitation = idSolicitation;
            this.value = value;
            this.user = user;
            this.restaurant = restaurant;
            this.status = inProgress;
            this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Transactional{" +
                "idSolicitation=" + idSolicitation +
                ", value=" + value +
                ", user=" + user +
                ", restaurant=" + restaurant +
                ", status=" + status +
                '}';
    }
}
