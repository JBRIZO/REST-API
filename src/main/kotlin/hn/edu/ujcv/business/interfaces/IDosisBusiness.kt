package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.DosisPaciente

interface IDosisBusiness {

    fun getDosisPaciente(): List<DosisPaciente>
    fun getDosisPacienteById(idDosisPaciente : Int) : DosisPaciente
    fun getDosisPacienteByPaciente(idPaciente : Int) : List<DosisPaciente>
    fun saveDosisPaciente(dosisPaciente: DosisPaciente): DosisPaciente
    fun saveDosisPaciente(dosisPaciente: List<DosisPaciente>): List<DosisPaciente>
    fun updateDosisPaciente(dosisPaciente: DosisPaciente):DosisPaciente
}