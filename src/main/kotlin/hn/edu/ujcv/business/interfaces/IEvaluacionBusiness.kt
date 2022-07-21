package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Evaluacion

interface IEvaluacionBusiness {
    fun getEvaluaciones():List<Evaluacion>
    fun getEvaluacionById(idEvaluacion:Int): Evaluacion
    fun getEvaluacionByIdUsuario(idUsuarioEvaluacion:Int): List<Evaluacion>
    fun getEvaluacionByIdPaciente(idPacienteEvaluacion:Int): Evaluacion
    fun getEvaluacionByIdEstado(idEstadoEvaluacion:Int): List<Evaluacion>
    fun getEvaluacionByIdCaso(idCasoEvaluacion:Int): List<Evaluacion>
    fun saveEvaluacion(evaluacion: Evaluacion): Evaluacion
    fun saveEvaluaciones(evaluaciones:List<Evaluacion>):List<Evaluacion>
    fun updateEvaluacion(evaluacion: Evaluacion): Evaluacion
}