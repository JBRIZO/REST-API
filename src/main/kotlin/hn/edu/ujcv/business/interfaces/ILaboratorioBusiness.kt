package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Laboratorio

interface ILaboratorioBusiness {
    fun getLaboratorio():List<Laboratorio>
    fun getLaboratorioById(idLaboratorio: Int):Laboratorio
    fun saveLaboratorio(laboratorio:Laboratorio):Laboratorio
    fun saveLaboratorios(laboratorio:List<Laboratorio>):List<Laboratorio>
    fun updateLaboratorio(laboratorio: Laboratorio):Laboratorio
    fun getLaboratorioByNombre(nombreLaboratorio:String):Laboratorio
}