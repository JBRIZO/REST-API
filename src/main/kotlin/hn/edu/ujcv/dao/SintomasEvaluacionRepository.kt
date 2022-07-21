package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.SintomasEvaluacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface SintomasEvaluacionRepository : JpaRepository<SintomasEvaluacion,Int>{
    fun findByIdEvaluacion(idEvaluacion: Int) : Optional<List<SintomasEvaluacion>>
    fun findByIdSintoma(idSintoma : Int) : Optional<List<SintomasEvaluacion>>
}