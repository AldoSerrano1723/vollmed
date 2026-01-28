package com.aldocursos.vollmed.modules.medico;

import com.aldocursos.vollmed.modules.direccion.DatosDireccion;

public record DatosActualizacionMedico(
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
