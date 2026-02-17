package com.aldocursos.vollmed.modules.consulta;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService servicio;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datosReservaConsulta) {
        var detalleConsulta = servicio.reservar(datosReservaConsulta);
        return ResponseEntity.ok(detalleConsulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelarConsulta datosCancelarConsulta) {
        servicio.cancelar(datosCancelarConsulta);
        return ResponseEntity.noContent().build();
    }
}
