package com.example.ecotrack.model.dto.response;

import java.sql.Date;

public record DicaEconomiaResponse(
        Long id,
        String titulo,
        String descricao,
        Date dataCriacao
) {}
