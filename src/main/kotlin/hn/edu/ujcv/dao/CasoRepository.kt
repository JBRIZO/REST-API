package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.TipoCaso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.util.MultiValueMap
import java.util.Optional
@Repository
interface CasoRepository:JpaRepository<TipoCaso,Int> {

    fun findByTipo(tipoCaso:String) :Optional<TipoCaso>

}