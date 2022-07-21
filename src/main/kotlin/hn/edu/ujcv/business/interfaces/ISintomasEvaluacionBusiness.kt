package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.SintomasEvaluacion


interface ISintomasEvaluacionBusiness {
    fun getSintomasEvaluacion(): List<SintomasEvaluacion>
    fun getSintomasEvaluacionById(idSintomasEvaluacion: Int): SintomasEvaluacion
    fun saveSintomasEvaluacion(sintomaEvaluacion: SintomasEvaluacion): SintomasEvaluacion
    fun saveSintomasEvaluacion(sintomasEvaluacion: List<SintomasEvaluacion>): List<SintomasEvaluacion>
    fun getByIdEvaluacion(idEvaluacion : Int) : List<SintomasEvaluacion>
    fun getByIdSintoma(idSintoma: Int) : List<SintomasEvaluacion>
    fun removeSintomasEvaluacion(idSintomasEvaluacion: Int)
}