package com.aldocursos.vollmed.modules.consulta.validaciones;

import com.aldocursos.vollmed.modules.consulta.ConsultaRepository;
import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos){
        var medicoTieneOtraConsultaEnElMismoHorario = repository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if (medicoTieneOtraConsultaEnElMismoHorario) {
            throw new RuntimeException("El médico ya tiene otra consulta agendada para el mismo horario");
        }
    }
}
