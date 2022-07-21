package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Rol
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RolRepository: JpaRepository<Rol,Int> {
    fun findByNombre(nombreRol:String):Optional <Rol>
}