package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Paciente
import java.time.LocalDate

interface IPacienteBusiness {
    fun getPacientes():List<Paciente>
    fun getPacienteById(idPaciente:Int): Paciente
    fun savePaciente(paciente: Paciente): Paciente
    fun savePacientes(pacientes:List<Paciente>):List<Paciente>
    fun updatePaciente(paciente: Paciente): Paciente
    fun getPacienteByNombre(nombrePaciente: String): Paciente
    fun getPacienteByIdColonia(idColoniaPaciente: Int): List<Paciente>
    fun getPacienteByTelefono(telefonoPaciente: String): Paciente
    fun getPacienteByDocumento(documentoPaciente: String): Paciente
    fun getPacienteByCorreo(correoPaciente: String): Paciente
}