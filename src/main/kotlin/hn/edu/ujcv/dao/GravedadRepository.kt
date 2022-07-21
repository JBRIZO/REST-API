package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Gravedad
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GravedadRepository: JpaRepository<Gravedad, Int> {
    fun findByNombre(nombreGravedad:String): Optional<Gravedad>
}