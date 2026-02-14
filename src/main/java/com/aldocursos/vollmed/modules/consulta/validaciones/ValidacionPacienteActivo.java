package com.aldocursos.vollmed.modules.consulta.validaciones;

import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;
import com.aldocursos.vollmed.modules.pacientes.PacienteRepository;

public class ValidacionPacienteActivo {
    private PacienteRepository repository;
    public void validar(DatosReservaConsulta datos){
        var pacienteEstaActivo = repository.findActivoById(datos.idPaciente());
        if(!pacienteEstaActivo){
            throw new RuntimeException("El paciente no se encuentra activo");
        }
    }
}
