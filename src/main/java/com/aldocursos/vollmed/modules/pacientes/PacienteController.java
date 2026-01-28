package com.aldocursos.vollmed.modules.pacientes;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public Page<DatosListaPaciente> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        return pacienteRepository.findAll(paginacion).map(DatosListaPaciente::new);
    }

    @PutMapping
    @Transactional
    public void actualizarPaciente(@RequestBody @Valid DatosActualizacionPaciente datosActualizacionPaciente) {
        var paciente = pacienteRepository.getReferenceById(datosActualizacionPaciente.id());
        paciente.actualizarInformaciones(datosActualizacionPaciente);
    }
}
