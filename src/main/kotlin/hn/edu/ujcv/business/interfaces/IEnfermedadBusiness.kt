package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Enfermedad

interface IEnfermedadBusiness {
    fun getEnfermedades():List<Enfermedad>
    fun getEnfermedadById(idEnfermedad:Int): Enfermedad
    fun saveEnfermedad(enfermedad: Enfermedad): Enfermedad
    fun saveEnfermedades(enfermedades:List<Enfermedad>):List<Enfermedad>
    fun updateEnfermedad(enfermedad: Enfermedad): Enfermedad
    fun getEnfermedadByNombre(nombreEnfermedad: String): Enfermedad
}