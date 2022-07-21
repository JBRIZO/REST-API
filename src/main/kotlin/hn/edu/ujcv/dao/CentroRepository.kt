package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Centro
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

 @Repository

    interface CentroRepository: JpaRepository<Centro, Int> {
        fun findByNombre(nombreCentro:String): Optional<Centro>
    }
