package com.aldocursos.vollmed.modules.medico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;


    @PostMapping()
    @Transactional
    public ResponseEntity<DatosDetalleMedico> registrar(@RequestBody @Valid DatosRegistroMedico datosMedico, UriComponentsBuilder  uriComponentsBuilder) {
        var medico = new Medico(datosMedico);
        medicoRepository.save(medico);
        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
    }

    @GetMapping()
    public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        var page = medicoRepository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DatosDetalleMedico> actualizarMedico(@RequestBody @Valid DatosActualizacionMedico datosActualizacionMedico) {
        var medico = medicoRepository.getReferenceById(datosActualizacionMedico.id());
        medico.actualizarInformacions(datosActualizacionMedico);

        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleMedico> detallesMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }
}
