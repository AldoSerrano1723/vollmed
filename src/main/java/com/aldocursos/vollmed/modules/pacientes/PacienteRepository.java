package com.aldocursos.vollmed.modules.pacientes;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByActivoTrue(Pageable paginacion);

    Boolean findActivoById(Long id);
}
