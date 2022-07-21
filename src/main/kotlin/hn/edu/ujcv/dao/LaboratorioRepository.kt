package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Laboratorio
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface LaboratorioRepository:JpaRepository<Laboratorio,Int> {

    fun findByNombre(nombreLaboratorio:String): Optional<Laboratorio>
}