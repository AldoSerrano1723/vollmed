package com.aldocursos.vollmed.modules.consulta;

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
        var medico = medicoRepository.findById(datos.idMedico()).get();
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
    }
}
