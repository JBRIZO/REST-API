package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Rol


interface IRolBusiness {
    fun getRol(): List<Rol>
    fun getRolById(idRol: Int): Rol
    fun saveRol(rol: Rol): Rol
    fun saveRol(rol:  List<Rol>): List<Rol>
    fun updateRol(rol: Rol): Rol
    fun getRolByNombre(nombre: String): Rol
}