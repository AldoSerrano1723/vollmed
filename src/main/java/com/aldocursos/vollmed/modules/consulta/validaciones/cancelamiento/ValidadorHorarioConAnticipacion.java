package com.aldocursos.vollmed.modules.consulta.validaciones.cancelamiento;

import com.aldocursos.vollmed.modules.ValidacionException;
import com.aldocursos.vollmed.modules.consulta.ConsultaRepository;
import com.aldocursos.vollmed.modules.consulta.DatosCancelarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioConAnticipacion implements ValidadorCancelamientoDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelarConsulta datos) {
        var consulta = repository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new ValidacionException("¡La consulta solo puede ser cancelada con anticipación mínima de 24 horas!");
        }
    }
}
