package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Gravedad

interface IGravedadBusiness {
    fun getGravedades():List<Gravedad>
    fun getGravedadById(idGravedad:Int): Gravedad
    fun saveGravedad(gravedad: Gravedad): Gravedad
    fun saveGravedades(gravedades:List<Gravedad>):List<Gravedad>
    fun updateGravedad(gravedad: Gravedad): Gravedad
    fun getGravedadByNombre(nombreGravedad: String): Gravedad
}