package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Sintoma

interface ISintomaBusiness {
    fun getSintoma():List<Sintoma>
    fun getSintomaById(idSintoma: Int):Sintoma
    fun saveSintoma(sintoma: Sintoma):Sintoma
    fun saveSintomas(sintoma:List<Sintoma>):List<Sintoma>
    fun updateSintoma(sintoma: Sintoma):Sintoma
    fun getSintomaByNombre(nombreSintoma: String):Sintoma
    fun getSintomasByIdGravedadSintoma(idGravedadSintoma : Int) : List<Sintoma>
}