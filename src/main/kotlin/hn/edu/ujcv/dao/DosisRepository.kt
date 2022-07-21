package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.DosisPaciente
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface DosisRepository: JpaRepository<DosisPaciente,Int> {

    fun findByIdPaciente(idPaciente : Int) : Optional<List<DosisPaciente>>
}