package com.aldocursos.vollmed.modules.medico;

import com.aldocursos.vollmed.modules.direccion.DatosDireccion;

public record DatosRegistroMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion
) {
}
