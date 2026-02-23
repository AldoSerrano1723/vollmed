package com.aldocursos.vollmed.modules.medico;

import com.aldocursos.vollmed.modules.direccion.DatosDireccion;
import com.aldocursos.vollmed.modules.direccion.Direccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosRegistroMedico> datosRegistroMedicoJson;

    @Autowired
    private JacksonTester<DatosDetalleMedico> datosDetalleMedicoJson;

    @MockitoBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Debería devolver código http 400 cuando las informaciones son inválidas")
    @WithMockUser
    void registrar_escenario1() throws Exception {
        var response = mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver código http 200 cuando las informaciones son válidas")
    @WithMockUser
    void registrar_escenario2() throws Exception {
        var datosRegistroMedico = new DatosRegistroMedico(
                "medico1",
                "medico@voll.med",
                "61999999999",
                "123456",
                Especialidad.CARDIOLOGIA,
                datosDireccion());

        var response = mvc
                .perform(post("/medicos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(datosRegistroMedicoJson.write(datosRegistroMedico).getJson()))
                .andReturn().getResponse();

        var datosDetalle = new DatosDetalleMedico(
                null,
                datosRegistroMedico.nombre(),
                datosRegistroMedico.email(),
                datosRegistroMedico.telefono(),
                datosRegistroMedico.documento(),
                datosRegistroMedico.especialidad(),
                new Direccion(datosRegistroMedico.direccion())
        );

        var jsonEsperado = datosDetalleMedicoJson.write(datosDetalle).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "rua xpto",
                "223434",
                "cidade z",
                "estado w",
                "1234",
                "pppp",
                "qqwer"
        );
    }
}