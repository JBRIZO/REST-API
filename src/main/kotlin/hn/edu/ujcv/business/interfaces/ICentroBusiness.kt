package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Centro


interface ICentroBusiness {
    fun getCentros():List<Centro>
    fun getCentroById(idCentro:Int): Centro
    fun saveCentro(centro: Centro): Centro
    fun saveCentros(centros:List<Centro>):List<Centro>
    fun updateCentro(centro: Centro): Centro
    fun getCentroByNombre(nombreCentro: String): Centro
}