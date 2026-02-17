package com.aldocursos.vollmed.modules.consulta.validaciones.reserva;

import com.aldocursos.vollmed.modules.ValidacionException;
import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacionFueraHorarioConsultas implements ValidadorDeConsultas{
    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeApertura = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierre = fechaConsulta.getHour() > 18;
        if(domingo || horarioDespuesDeCierre || horarioAntesDeApertura){
            throw new ValidacionException("Horario seleccionado fuera dela horario de antencion. Horario de atención: Lunes a Sábado de 7 a 19hs");
        }
    }
}
