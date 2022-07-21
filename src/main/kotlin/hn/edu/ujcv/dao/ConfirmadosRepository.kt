package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Confirmados
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ConfirmadosRepository :JpaRepository<Confirmados,Int>{
    fun findByRecuperado(recuperado:Boolean): Optional<List<Confirmados>>
    fun findByFallecido(fallecido:Boolean): Optional<List<Confirmados>>
    fun findByIdPaciente(idPaciente : Int) : Optional<Confirmados>
}