package com.aldocursos.vollmed.modules.pacientes;

import com.aldocursos.vollmed.modules.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroPaciente(
       @NotBlank String nombre,
       @NotBlank String email,
       @NotBlank String telefono,
       @NotBlank String documento,
       @NotNull @Valid DatosDireccion direccion
) {
}
