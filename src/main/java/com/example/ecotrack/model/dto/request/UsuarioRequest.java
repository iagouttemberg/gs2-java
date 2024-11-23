package com.example.ecotrack.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
        @NotNull String firebaseID,
        @NotNull String nome,
        @NotNull @Email String email
) {}

