package com.aldocursos.vollmed.modules.consulta.validaciones.reserva;

import com.aldocursos.vollmed.modules.ValidacionException;
import com.aldocursos.vollmed.modules.consulta.ConsultaRepository;
import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos){
        var medicoTieneOtraConsultaEnElMismoHorario = repository.existsByMedicoIdAndFechaAndMotivoIsNull(datos.idMedico(), datos.fecha());
        if (medicoTieneOtraConsultaEnElMismoHorario) {
            throw new ValidacionException("El médico ya tiene otra consulta agendada para el mismo horario");
        }
    }
}
