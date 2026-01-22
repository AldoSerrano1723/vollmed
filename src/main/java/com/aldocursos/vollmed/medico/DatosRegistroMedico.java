package com.aldocursos.vollmed.medico;

import com.aldocursos.vollmed.direccion.DatosDireccion;

public record DatosRegistroMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion
) {
}
