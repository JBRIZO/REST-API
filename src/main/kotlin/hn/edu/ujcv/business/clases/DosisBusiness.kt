package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IDosisBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.DosisRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.DosisPaciente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.xml.crypto.Data

@Service
class DosisBusiness: IDosisBusiness {
    @Autowired
    val dosisRepository: DosisRepository?=null
    override fun getDosisPaciente(): List<DosisPaciente> {
        try {
            return dosisRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getDosisPacienteById(idDosisPaciente: Int): DosisPaciente {
        val opt: Optional<DosisPaciente>
        try {
            opt=dosisRepository!!.findById(idDosisPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la dosis con id: $idDosisPaciente")
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveDosisPaciente(dosisPaciente: DosisPaciente): DosisPaciente {
        try {
            //lote
            if (dosisPaciente.lote.isEmpty())
                throw BusinessException("El lote no puede ir en blanco.")
            if (dosisPaciente.lote.length < 6)
                throw BusinessException("El lote no puede ser tan corto.")
            if (dosisPaciente.lote.length > 12)
                throw BusinessException("El tipo no puede contener tantos caracteres.")
           if(!dosisPaciente.lote.matches(Regex("^[^\\W_]*\$")))
               throw BusinessException("El lote solo puede contener letras y numeros")
            //fecha
            if (dosisPaciente.fecha!!.isAfter(LocalDate.now()))
                throw BusinessException("La fecha no puede ir despues de la fecha actual.")

            return dosisRepository!!.save(dosisPaciente)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("El paciente no puede tener dos dosis de vacunas aplicadas el mismo dia.")
        }catch (e : Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveDosisPaciente(dosisPacienteList: List<DosisPaciente>): List<DosisPaciente> {
        try {
            for(dosisPaciente in dosisPacienteList){
                //lote
                if (dosisPaciente.lote.isEmpty())
                    throw BusinessException("El lote no puede ir en blanco.")
                if (dosisPaciente.lote.length < 6)
                    throw BusinessException("El lote no puede ser tan corto.")
                if (dosisPaciente.lote.length > 12)
                    throw BusinessException("El tipo no puede contener tantos caracteres.")
                if(!dosisPaciente.lote.matches(Regex("^[^\\W_]*\$")))
                    throw BusinessException("El lote solo puede contener letras y numeros")
                //fecha
                if (dosisPaciente.fecha!!.isAfter(LocalDate.now()))
                    throw BusinessException("La fecha no puede ir despues de la fecha actual.")
            }
            return dosisRepository!!.saveAll(dosisPacienteList)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("El paciente no puede tener dos dosis de vacunas aplicadas el mismo dia.")
        }catch (e : Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateDosisPaciente(dosisPaciente: DosisPaciente): DosisPaciente {
        val opt: Optional<DosisPaciente>
        try {
            opt=dosisRepository!!.findById(dosisPaciente.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la dosis ${dosisPaciente.id}")
        else{
            try {
                //lote
                if (dosisPaciente.lote.isEmpty())
                    throw BusinessException("El lote no puede ir en blanco.")
                if (dosisPaciente.lote.length < 6)
                    throw BusinessException("El lote no puede ser tan corto.")
                if (dosisPaciente.lote.length > 12)
                    throw BusinessException("El tipo no puede contener tantos caracteres.")
                if(!dosisPaciente.lote.matches(Regex("^[^\\W_]*\$")))
                    throw BusinessException("El lote solo puede contener letras y numeros")
                //fecha
                if (dosisPaciente.fecha!!.isAfter(LocalDate.now()))
                    throw BusinessException("La fecha no puede ir despues de la fecha actual.")
                dosisRepository!!.save(dosisPaciente)
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("El paciente no puede tener dos dosis de vacunas aplicadas el mismo dia.")
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getDosisPacienteByPaciente(idPaciente: Int): List<DosisPaciente> {
        val opt: Optional<List<DosisPaciente>>
        try {
            opt=dosisRepository!!.findByIdPaciente(idPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontraron dosis con id de paciente: $idPaciente")
        return opt.get()
    }



}