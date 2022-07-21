package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.TipoCaso

interface ICasoBusiness {
    fun getTipoCaso(): List<TipoCaso>
    fun getTipoCasoById(idTipoCaso: Int): TipoCaso
    fun saveTipoCaso(tipoCaso: TipoCaso): TipoCaso
    fun saveTipoCaso(tipoCaso: List<TipoCaso>): List<TipoCaso>
    fun updateTipoCaso(tipoCaso: TipoCaso): TipoCaso
    fun getTipoCasoByTipo(tipo: String): TipoCaso
}