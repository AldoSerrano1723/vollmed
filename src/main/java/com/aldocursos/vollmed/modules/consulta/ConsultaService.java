package com.aldocursos.vollmed.modules.consulta;

import com.aldocursos.vollmed.modules.ValidacionException;
import com.aldocursos.vollmed.modules.consulta.validaciones.ValidadorDeConsultas;
import com.aldocursos.vollmed.modules.medico.Medico;
import com.aldocursos.vollmed.modules.medico.MedicoRepository;
import com.aldocursos.vollmed.modules.pacientes.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadores;

    public void reservar(DatosReservaConsulta datos) {
        // Lógica para reservar una consulta
        if(!pacienteRepository.existsById(datos.idPaciente())) {
            throw new ValidacionException("ID de paciente no existe");
        }
        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionException("ID de médico no existe");
        }

        // Validar reglas de negocio
        validadores.forEach(v -> v.validar(datos));

        var medico = elegeriMedico(datos);
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha(), null);
        consultaRepository.save(consulta);
    }

    private Medico elegeriMedico(DatosReservaConsulta datos) {
        if(datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null) {
            throw new ValidacionException("Especialidad es requerida cuando no se especifica un médico");
        }

        return medicoRepository.eleqirMedicoAleatorioPorEspecialidad(datos.especialidad(), datos.fecha());
    }

    public void cancelar(DatosCancelarConsulta datosCancelarConsulta) {
        if(!consultaRepository.existsById(datosCancelarConsulta.idConsulta())) {
            throw new ValidacionException("ID de consulta no existe");
        }
        var consulta = consultaRepository.getReferenceById(datosCancelarConsulta.idConsulta());
        consulta.cancelar(datosCancelarConsulta.motivo());
    }
}
