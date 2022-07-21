package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IEnfermedadesBasePacienteBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.EnfermedadesBasePacienteRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.EnfermedadBasePaciente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class EnfermedadesBasePacienteBusiness : IEnfermedadesBasePacienteBusiness{

    @Autowired
    val enfermedadesBasePacienteRepository: EnfermedadesBasePacienteRepository? = null

    @Throws(BusinessException::class)
    override fun getEnfermedadesBasePaciente(): List<EnfermedadBasePaciente> {
        try {
            return enfermedadesBasePacienteRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveEnfermedadBasePaciente(enfermedad: EnfermedadBasePaciente): EnfermedadBasePaciente {
        try {
            return enfermedadesBasePacienteRepository!!.save(enfermedad)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Esa enfermedad ya esta asociada a ese paciente")
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveEnfermedadBasePaciente(enfermedades: List<EnfermedadBasePaciente>): List<EnfermedadBasePaciente> {
        try {
            return enfermedadesBasePacienteRepository!!.saveAll(enfermedades)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Esa enfermedad ya esta asociada a ese paciente.")
        }catch (e : Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getByIdEnfermedadBase(idEnfermedadBase: Int): List<EnfermedadBasePaciente> {
        val opt: Optional<List<EnfermedadBasePaciente>>
        try {
            opt=enfermedadesBasePacienteRepository!!.findByIdEnfermedadBase(idEnfermedadBase)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro  registro con id de enfermedad base: $idEnfermedadBase")
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getByIdPaciente(idPaciente: Int): List<EnfermedadBasePaciente> {
        val opt: Optional<List<EnfermedadBasePaciente>>
        try {
            opt=enfermedadesBasePacienteRepository!!.findByIdPaciente(idPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontraron registros con id de paciente: $idPaciente")
        return opt.get()
    }

}