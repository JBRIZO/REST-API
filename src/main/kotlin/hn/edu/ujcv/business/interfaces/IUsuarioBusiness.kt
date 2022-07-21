package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Usuario
import java.time.LocalDate

interface IUsuarioBusiness {
    fun getUsuarios():List<Usuario>
    fun getUsuarioById(idUsuario:Int):Usuario
    fun getUsuarioByNombre(nombreUsuario:String):Usuario
    fun getUsuarioByApellido(apellidoUsuario:String):Usuario
    fun getUsuarioByDocumento(documentoUsuario:String):Usuario
    fun getUsuarioByRol(rolUsuario: Int):List<Usuario>
    fun getUsuarioByTelefono(telefonoUsuario: String):Usuario
    fun getUsuarioByCorreo(correoUsuario:String):Usuario
    fun getUsuarioByFechaNacimiento(fechaNacimientoUsuario:LocalDate?=null):Usuario
    fun getUsuarioByNombreUsuario(nombreUsuario: String) : Usuario

    fun saveUsuario(usuario:Usuario):Usuario
    fun saveUsuarios(usuario:List<Usuario>):List<Usuario>
    fun updateUsuario(usuario: Usuario):Usuario
}