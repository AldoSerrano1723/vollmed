package com.aldocursos.vollmed.modules.medico;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

   Page<Medico> findAllByActivoTrue(Pageable paginacion);

   // Método para seleccionar un médico aleatorio disponible por especialidad y fecha
   @Query("""
        select m from Medico m
        where
        m.activo = true
        and
        m.especialidad = :especialidad
        and m.id not in (
            select c.medico.id from Consulta c
            where
            c.fecha = :fecha
            and
            c.motivo is null
        )
        order by rand()
        limit 1
        """)
   Medico eleqirMedicoAleatorioPorEspecialidad(Especialidad especialidad, LocalDateTime fecha);

   @Query("""
        select m.activo
        from Medico m
        where 
        m.id = :idMedico
        """)
   Boolean findActivoById(Long idMedico);
}
