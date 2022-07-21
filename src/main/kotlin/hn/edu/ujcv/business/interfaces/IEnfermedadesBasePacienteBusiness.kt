package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.EnfermedadBasePaciente


interface IEnfermedadesBasePacienteBusiness {
    fun getEnfermedadesBasePaciente(): List<EnfermedadBasePaciente>
    fun saveEnfermedadBasePaciente(enfermedadBasePaciente: EnfermedadBasePaciente): EnfermedadBasePaciente
    fun saveEnfermedadBasePaciente(enfermedadBasePaciente: List<EnfermedadBasePaciente>): List<EnfermedadBasePaciente>
    fun getByIdPaciente(idPaciente : Int) : List<EnfermedadBasePaciente>
    fun getByIdEnfermedadBase(idEnfermedadBase: Int) : List<EnfermedadBasePaciente>
}