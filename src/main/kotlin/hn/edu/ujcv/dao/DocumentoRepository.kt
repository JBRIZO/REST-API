package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.TipoDocumento
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface DocumentoRepository:JpaRepository<TipoDocumento,Int>{
    fun findByNombre(nombre: String):Optional<TipoDocumento>


}