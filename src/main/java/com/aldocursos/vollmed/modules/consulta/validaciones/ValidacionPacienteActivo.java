package com.aldocursos.vollmed.modules.consulta.validaciones;

import com.aldocursos.vollmed.modules.ValidacionException;
import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;
import com.aldocursos.vollmed.modules.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionPacienteActivo implements ValidadorDeConsultas{

    @Autowired
    private PacienteRepository repository;

    public void validar(DatosReservaConsulta datos){
        var pacienteEstaActivo = repository.findActivoById(datos.idPaciente());
        if(!pacienteEstaActivo){
            throw new ValidacionException("El paciente no se encuentra activo");
        }
    }
}
