package com.aldocursos.vollmed.modules.consulta.validaciones;

import com.aldocursos.vollmed.modules.consulta.DatosReservaConsulta;
import com.aldocursos.vollmed.modules.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionMedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository repository;

    public void validar(DatosReservaConsulta datos){
        if (datos.idMedico() == null){
            return;
        }
        var medicoEstaActivo = repository.findActivoById(datos.idMedico());
        if(!medicoEstaActivo){
            throw new RuntimeException("El médico no se encuentra activo");
        }
    }
}

