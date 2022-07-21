package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Confirmados

interface IConfirmadosBusiness {
    fun getConfirmados(): List<Confirmados>
    fun getConfirmadosById(idConfirmados: Int): Confirmados
    fun saveConfirmados(confirmados: Confirmados): Confirmados
    fun saveConfirmados(confirmados: List<Confirmados>): List<Confirmados>
    fun updateConfirmados(confirmados: Confirmados): Confirmados
    fun getConfirmadoByRecuperado(recuperado: Boolean): List<Confirmados>
    fun getConfirmadoByFallecido(fallecido: Boolean): List<Confirmados>
    fun getConfirmadoByIdPaciente(idPaciente : Int) : Confirmados
}