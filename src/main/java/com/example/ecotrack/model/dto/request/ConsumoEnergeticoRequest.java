package com.example.ecotrack.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record ConsumoEnergeticoRequest(
        @NotNull String mes,
        @NotNull int ano,
        @NotNull Double consumoKWh,
        @NotNull Long usuarioId
) {}
