package com.aldocursos.vollmed.modules.medico;

import com.aldocursos.vollmed.modules.direccion.Direccion;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    private String telefono;
    private String documento;
    private Boolean activo;

    @Enumerated (EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    //CONSTRUCTORES
    public Medico(DatosRegistroMedico datosMedico) {
        this.nombre = datosMedico.nombre();
        this.email = datosMedico.email();
        this.telefono = datosMedico.telefono();
        this.documento = datosMedico.documento();
        this.activo = true;
        this.especialidad = datosMedico.especialidad();
        this.direccion = new Direccion(datosMedico.direccion());
    }

    //METODOS
    public void actualizarInformacions(@Valid DatosActualizacionMedico datosActualizacionMedico) {
        if (datosActualizacionMedico.nombre() != null) {
            this.nombre = datosActualizacionMedico.nombre();
        }
        if (datosActualizacionMedico.telefono() != null) {
            this.telefono = datosActualizacionMedico.telefono();
        }
        if (datosActualizacionMedico.direccion() != null) {
            this.direccion.actualizarDireccion(datosActualizacionMedico.direccion());
        }
    }

    public void eliminar(){
        this.activo = false;
    }
}
