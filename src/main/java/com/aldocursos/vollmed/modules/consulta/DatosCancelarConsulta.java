package com.aldocursos.vollmed.modules.consulta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelarConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCanelacion motivo
) {
}
