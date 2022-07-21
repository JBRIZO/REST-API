package hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Paciente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface PacienteRepository: JpaRepository<Paciente, Int> {
    fun findByNombre(nombrePaciente:String): Optional<Paciente>
    fun findByColonia(idColoniaPaciente:Int): Optional<List<Paciente>>
    fun findByTelefono(telefonoPaciente:String): Optional<Paciente>
    fun findByDocumento(documentoPaciente:String): Optional<Paciente>
    fun findByCorreo(correoPaciente:String): Optional<Paciente>
}