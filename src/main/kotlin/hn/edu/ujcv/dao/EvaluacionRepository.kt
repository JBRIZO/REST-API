package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Evaluacion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EvaluacionRepository: JpaRepository<Evaluacion, Int> {
    fun findByIdUsuario(idUsuarioEvaluacion:Int): Optional<List<Evaluacion>>
    fun findByIdPaciente(idPacienteEvaluacion:Int): Optional<Evaluacion>
    fun findByIdEstado(idEstadoEvaluacion:Int): Optional<List<Evaluacion>>
    fun findByIdTipoCaso(casoEvaluacion:Int): Optional<List<Evaluacion>>
}