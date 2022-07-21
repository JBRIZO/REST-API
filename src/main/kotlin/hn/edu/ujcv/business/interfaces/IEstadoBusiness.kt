package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Estado

interface IEstadoBusiness {
    fun getEstado():List<Estado>
    fun getEstadoById(idEstado:Int): Estado
    fun saveEstado(estado:Estado):Estado
    fun saveEstados(estado:List<Estado>):List<Estado>
    fun updateEstado(estado: Estado):Estado
    fun getEstadoByNombre(nombreEstado: String):Estado
}