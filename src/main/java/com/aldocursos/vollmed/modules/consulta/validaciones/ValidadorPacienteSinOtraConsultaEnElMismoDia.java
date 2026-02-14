package com.aldocursos.vollmed.modules.consulta.validaciones;

import com.aldocursos.vollmed.modules.consulta.ConsultaRepository;
import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;

public class ValidadorPacienteSinOtraConsultaEnElMismoDia {
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElMismoDia = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if(pacienteTieneOtraConsultaEnElMismoDia){
            throw new RuntimeException("El paciente ya tiene otra consulta agendada para el mismo día");
        }
    }
}
