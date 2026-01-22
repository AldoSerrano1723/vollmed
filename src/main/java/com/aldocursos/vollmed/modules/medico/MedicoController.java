package com.aldocursos.vollmed.modules.medico;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping()
    public void registrar(@RequestBody DatosRegistroMedico registroMedico) {
        System.out.println(registroMedico);
    }
}
