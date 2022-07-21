package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Sintoma
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface SintomaRepository:JpaRepository<Sintoma,Int> {
    fun findByNombre(nombreSintoma:String): Optional<Sintoma>
    fun findByGravedad(IdGravedadSintoma : Int) : Optional<List<Sintoma>>
}