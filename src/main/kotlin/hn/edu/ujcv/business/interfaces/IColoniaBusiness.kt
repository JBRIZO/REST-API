package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Colonias

interface IColoniaBusiness {
    fun getColonia():List<Colonias>
    fun getColoniaById(idColonia:Int): Colonias
    fun saveColonia(colonia: Colonias): Colonias
    fun saveColonias(colonia:List<Colonias>):List<Colonias>
    fun updateColonia(colonia:Colonias):Colonias
    fun getColoniaByNombre(nombreColonia:String): Colonias
}