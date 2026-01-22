package com.aldocursos.vollmed.direccion;

public record DatosDireccion(
        String calle,
        String numero,
        String complemento,
        String barrio,
        String codigo_postal,
        String ciudad,
        String estado
) {
}
