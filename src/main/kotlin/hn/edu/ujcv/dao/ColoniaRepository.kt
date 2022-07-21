package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Colonias
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ColoniaRepository: JpaRepository<Colonias, Int> {
    fun findByNombre(nombreColonia: String): Optional<Colonias>
}
