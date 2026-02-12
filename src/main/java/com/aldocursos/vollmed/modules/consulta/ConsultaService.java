package com.aldocursos.vollmed.modules.consulta;

import com.aldocursos.vollmed.modules.ValidacionException;
import com.aldocursos.vollmed.modules.medico.Medico;
import com.aldocursos.vollmed.modules.medico.MedicoRepository;
import com.aldocursos.vollmed.modules.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void reservar(DatosReservaConsulta datos) {
        // Lógica para reservar una consulta
        if(!pacienteRepository.existsById(datos.idPaciente())) {
            throw new ValidacionException("ID de paciente no existe");
        }
        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionException("ID de médico no existe");
        }

        var medico = elegeriMedico(datos);
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico elegeriMedico(DatosReservaConsulta datos) {
        return null;
    }
}
