package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "paciente")
data class Paciente(@Column(name = "id_tipo_documento")val tipoDocumento : Int = 0,
                    @Column(name = "id_colonia")val colonia : Int = 0,
                    val documento:String="",
                    var nombre:String="",
                    var apellido:String="",
                    var direccion: String="",
                    val telefono:String = "",
                    val correo:String="",
                    @Column(name = "fecha_nacimiento")val fechaNacimiento: LocalDate?=null){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
