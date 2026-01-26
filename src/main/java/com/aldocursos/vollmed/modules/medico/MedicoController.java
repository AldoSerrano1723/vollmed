package com.aldocursos.vollmed.modules.medico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping()
    public void registrar(@RequestBody @Valid DatosRegistroMedico datosMedico) {
        Medico medico = new Medico(datosMedico);
        medicoRepository.save(medico);
    }

    @GetMapping()
    public Page<DatosListaMedico> listar(Pageable paginacion) {
        return medicoRepository.findAll(paginacion).map(DatosListaMedico::new);
    }
}
