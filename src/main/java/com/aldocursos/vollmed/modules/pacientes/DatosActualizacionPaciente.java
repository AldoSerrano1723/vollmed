package com.aldocursos.vollmed.modules.pacientes;

import com.aldocursos.vollmed.modules.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPaciente(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
