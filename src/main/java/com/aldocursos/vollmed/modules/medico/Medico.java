package com.aldocursos.vollmed.modules.medico;

import com.aldocursos.vollmed.modules.direccion.DatosDireccion;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "medicos")
public class Medico {
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documento;

    @Enumerated (EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private DatosDireccion direccion;

    //CONSTRUCTORES
    public Medico(DatosRegistroMedico datosMedico) {
        this.nombre = datosMedico.nombre();
        this.email = datosMedico.email();
        this.documento = datosMedico.documento();
        this.especialidad = datosMedico.especialidad();
        this.direccion = datosMedico.direccion();
    }
}
