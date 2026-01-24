package com.aldocursos.vollmed.modules.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping()
    public void registrar(@RequestBody DatosRegistroMedico datosMedico) {
        Medico medico = new Medico(datosMedico);
        medicoRepository.save(medico);
    }
}
