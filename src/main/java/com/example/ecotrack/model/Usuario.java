package com.example.ecotrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @NotNull
    private @Getter @Setter String firebaseID;

    @NotNull
    private @Getter @Setter String nome;

    @NotNull
    @Email
    private @Getter @Setter String email;

    @NotNull
    private @Getter @Setter Date dataCadastro;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private @Getter @Setter List<ConsumoEnergetico> consumosEnergeticos = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.dataCadastro = Date.valueOf(LocalDate.now());
    }
}
