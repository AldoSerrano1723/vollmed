package com.aldocursos.vollmed.modules.consulta.validaciones;

import com.aldocursos.vollmed.modules.ValidacionException;
import com.aldocursos.vollmed.modules.consulta.ConsultaRepository;
import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSinOtraConsultaEnElMismoDia implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElMismoDia = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if(pacienteTieneOtraConsultaEnElMismoDia){
            throw new ValidacionException("El paciente ya tiene otra consulta agendada para el mismo día");
        }
    }
}
