package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface UsuarioRepository:JpaRepository<Usuario,Int> {
    fun findByRol(rolUsuario:Int):Optional<List<Usuario>>
    fun findByNombre(nombreEmpleado:String): Optional<Usuario>
    fun findByApellido(apellidoUsuario:String): Optional<Usuario>
    fun findByCorreo(correoUsuario:String): Optional<Usuario>
    fun findByTelefono(telefonoUsuario:String):Optional<Usuario>
    fun findByFechaNacimiento(fechaNacimientoUsuario:LocalDate?=null):Optional<Usuario>
    fun findByDocumento(documentoUsuario:String): Optional<Usuario>
    fun findByNombreUsuario(nombreUsuario : String) : Optional<Usuario>
}