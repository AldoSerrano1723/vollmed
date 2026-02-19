package com.aldocursos.vollmed.modules.medico;

import com.aldocursos.vollmed.modules.consulta.Consulta;
import com.aldocursos.vollmed.modules.direccion.DatosDireccion;
import com.aldocursos.vollmed.modules.pacientes.DatosRegistroPaciente;
import com.aldocursos.vollmed.modules.pacientes.Paciente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Deveria de devolver null cuando el medico buscado no esta disponible en esa fecha")
    void eleqirMedicoAleatorioPorEspecialidadEscenario1() {

        //given o arrange - preparar el escenario para la prueba
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("medico1", "medico@vollmed.com", "123456789", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("paciente1", "paciente@gmail.com", "123456789");
        registrarConsulta(medico, paciente, lunesSiguienteALas10);

        //when o act - ejecutar la acción que queremos probar
        var medicoLibre = repository.eleqirMedicoAleatorioPorEspecialidad(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);

        //then o assert - verificar el resultado de la acción
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Deveria de devolver medico cuando el medico buscado esta disponible en esa fecha")
    void eleqirMedicoAleatorioPorEspecialidadEscenario2() {

        //given o arrange - preparar el escenario para la prueba
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("medico1", "medico@vollmed.com", "123456789", Especialidad.CARDIOLOGIA);

        //when o act - ejecutar la acción que queremos probar
        var medicoLibre = repository.eleqirMedicoAleatorioPorEspecialidad(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);

        //then o assert - verificar el resultado de la acción
        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento){
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "123456789",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento){
        return new DatosRegistroPaciente(
                nombre,
                email,
                "123456789",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "calle 123",
                "233",
                "complemento",
                "provincia",
                "12345",
                "jalisco",
                "california"
        );
    }
}