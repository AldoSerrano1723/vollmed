package com.aldocursos.vollmed.modules.pacientes;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    @PostMapping()
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datosPaciente) {
        Paciente paciente = new Paciente(datosPaciente);
        pacienteRepository.save(paciente);
    }
}
