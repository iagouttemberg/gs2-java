package com.example.ecotrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
public class ConsumoEnergetico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @NotNull
    private @Getter @Setter String mes;

    @NotNull
    private @Getter @Setter int ano;

    @NotNull
    private @Getter @Setter Double consumoKWh;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private @Getter @Setter Usuario usuario;

}
