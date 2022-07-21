package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.EnfermedadBasePaciente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface EnfermedadesBasePacienteRepository : JpaRepository<EnfermedadBasePaciente,Int> {
    fun findByIdPaciente(idPaciente : Int):Optional<List<EnfermedadBasePaciente>>
    fun findByIdEnfermedadBase(idEnfermedadBase : Int) : Optional<List<EnfermedadBasePaciente>>
}