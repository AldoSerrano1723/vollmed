package com.aldocursos.vollmed.modules.consulta.validaciones;

import com.aldocursos.vollmed.modules.ValidacionException;
import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidacionConsultaConAnticipacion {
    public void validar(DatosReservaConsulta datos){
        var fechaDeConsulta = datos.fecha();
        var ahora =  LocalDateTime.now();
        var diferennciaEnMinutos = Duration.between(ahora, fechaDeConsulta).toMinutes();
        if(diferennciaEnMinutos < 30){
            throw new ValidacionException("La consulta debe reservarse con al menos 30 minutos de anticipación");
        }
    }
}
