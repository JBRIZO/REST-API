package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.FabricanteVacuna
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface FabricanteRepository:JpaRepository<FabricanteVacuna,Int>{

    fun findByNombre(nombre: String): Optional<FabricanteVacuna>

}