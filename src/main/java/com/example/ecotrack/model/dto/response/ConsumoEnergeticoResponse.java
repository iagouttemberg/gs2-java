package com.example.ecotrack.model.dto.response;

public record ConsumoEnergeticoResponse(
        Long id,
        String mes,
        int ano,
        Double consumoKWh,
        Long usuarioId
) {}

