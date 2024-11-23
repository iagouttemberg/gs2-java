package com.example.ecotrack.model.dto.response;

import java.sql.Date;
import java.util.List;

public record UsuarioResponse(
        Long id,
        String firebaseID,
        String nome,
        String email,
        Date dataCadastro,
        List<ConsumoEnergeticoResponse> consumosEnergeticos
) {}

