package com.aldocursos.vollmed.modules.pacientes;

import com.aldocursos.vollmed.modules.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Embedded
    private Direccion direccion;

    //CONSTRUCTORES
    public Paciente(DatosRegistroPaciente datosPaciente) {
        this.nombre = datosPaciente.nombre();
        this.email = datosPaciente.email();
        this.telefono = datosPaciente.telefono();
        this.documento = datosPaciente.documento();
        this.direccion = datosPaciente.direccion();
    }
}
