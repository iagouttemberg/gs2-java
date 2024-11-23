package com.example.ecotrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
public class DicaEconomia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @NotNull
    private @Getter @Setter String titulo;

    @NotNull
    private @Getter @Setter String descricao;

    @NotNull
    private @Getter @Setter Date dataCriacao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = Date.valueOf(LocalDate.now());
    }
}
