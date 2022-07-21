package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Enfermedad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EnfermedadRepository: JpaRepository<Enfermedad, Int> {
    fun findByNombre(nombreEnfermedad:String): Optional<Enfermedad>
}