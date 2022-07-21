package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "usuario")
data class Usuario(@Column(name = "id_tipo_documento")val idTipoDocumento : Int = 0,
                   @Column(name = "id_rol")val rol : Int = 0,
                   var nombre:String = "",
                   var apellido:String = "",
                   val correo:String = "",
                   var direccion:String = "",
                   val telefono:String = "",
                   @Column(name = "fecha_nacimiento")val fechaNacimiento:LocalDate?=null,
                   val documento:String = "",
                   @Column(name = "nombre_usuario") val nombreUsuario:String = "",
                   var contrasena:String = "",
                   @Column(name = "fecha_cambio_contrasena")val fechaCambioContrasena:LocalDate?=null,
                   val activo:Boolean = false){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}


