package com.example.ecotrack.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record DicaEconomiaRequest(
        @NotNull String titulo,
        @NotNull String descricao
) {}

