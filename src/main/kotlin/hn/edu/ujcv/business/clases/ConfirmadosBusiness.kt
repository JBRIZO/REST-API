package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IConfirmadosBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.ConfirmadosRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Confirmados
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
class ConfirmadosBusiness : IConfirmadosBusiness {
    @Autowired
    val confirmadosRepository: ConfirmadosRepository?=null
    @Throws(BusinessException::class)
    override fun getConfirmados(): List<Confirmados> {
        try {
            return confirmadosRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getConfirmadosById(idConfirmados: Int): Confirmados {
        val opt: Optional<Confirmados>
        try {
            opt=confirmadosRepository!!.findById(idConfirmados)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el paciente confirmado con id $idConfirmados")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveConfirmados(confirmado: Confirmados): Confirmados {
        try {
            if (confirmado.fechaExamen!!.isAfter(LocalDateTime.now()))
                throw BusinessException("La fecha de realizacion del examen debe ser antes de la fecha actual para un paciente confirmado.")
            if (confirmado.recuperado && confirmado.fallecido)
                throw BusinessException("El paciente confirmado no puede estar recuperado y fallecido al mismo tiempo.")
            if (confirmado.recuperado && confirmado.fechaRecuperacion == null)
                throw BusinessException("Se debe especificar la fecha en la que se recupero el paciente.")
            if (confirmado.fallecido && confirmado.fechaDeceso == null)
                throw BusinessException("Se debe especificar la fecha en la que fallecio el paciente.")
            if (confirmado.recuperado && confirmado.fechaDeceso != null)
                throw BusinessException("No se debe especificar la fecha de deceso en un paciente recuperado.")
            if (confirmado.fallecido && confirmado.fechaRecuperacion != null)
                throw BusinessException("No se debe especificar la fecha de recuperacion de un paciente fallecido.")
            if (confirmado.fechaEntregaResultado!!.isBefore(confirmado.fechaExamen))
                throw BusinessException("La fecha de entrega del resultado no puede ser antes de la fecha de realizacion del examen.")
            if (confirmado.fechaRecuperacion != null) {
                if (!confirmado.recuperado)
                    throw BusinessException("Debes especificar que el paciente esta recuperado para detallar una fecha de recuperacion.")
                if (confirmado.fechaRecuperacion.isBefore(confirmado.fechaEntregaResultado.toLocalDate()))
                    throw BusinessException("La fecha de recuperacion no puede ser antes de la fecha de entrega del resultado del examen.")
                }
            if (confirmado.fechaDeceso != null) {
                if (!confirmado.fallecido)
                    throw BusinessException("Debes especificar que el paciente ha fallecido para detallar una fecha de deceso.")
                if (confirmado.fechaDeceso.isBefore(confirmado.fechaEntregaResultado.toLocalDate()))
                    throw BusinessException("La fecha de deceso no puede ser antes de la fecha de entrega del resultado del examen.")
            }

            return confirmadosRepository!!.save(confirmado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveConfirmados(confirmados: List<Confirmados>): List<Confirmados> {
        try {
            for (confirmado in confirmados){
                if (confirmado.fechaExamen!!.isAfter(LocalDateTime.now()))
                    throw BusinessException("La fecha de realizacion del examen debe ser antes de la fecha actual para un paciente confirmado.")
                if (confirmado.recuperado && confirmado.fallecido)
                    throw BusinessException("El paciente confirmado no puede estar recuperado y fallecido al mismo tiempo.")
                if (confirmado.recuperado && confirmado.fechaRecuperacion == null)
                    throw BusinessException("Se debe especificar la fecha en la que se recupero el paciente.")
                if (confirmado.fallecido && confirmado.fechaDeceso == null)
                    throw BusinessException("Se debe especificar la fecha en la que fallecio el paciente.")
                if (confirmado.recuperado && confirmado.fechaDeceso != null)
                    throw BusinessException("No se debe especificar la fecha de deceso en un paciente recuperado.")
                if (confirmado.fallecido && confirmado.fechaRecuperacion != null)
                    throw BusinessException("No se debe especificar la fecha de recuperacion de un paciente fallecido.")
                if (confirmado.fechaEntregaResultado!!.isBefore(confirmado.fechaExamen))
                    throw BusinessException("La fecha de entrega del resultado no puede ser antes de la fecha de realizacion del examen.")
                if (confirmado.fechaRecuperacion != null) {
                    if (!confirmado.recuperado)
                        throw BusinessException("Debes especificar que el paciente esta recuperado para detallar una fecha de recuperacion.")
                    if (confirmado.fechaRecuperacion.isBefore(confirmado.fechaEntregaResultado.toLocalDate()))
                        throw BusinessException("La fecha de recuperacion no puede ser antes de la fecha de entrega del resultado del examen.")
                }
                if (confirmado.fechaDeceso != null) {
                    if (!confirmado.fallecido)
                        throw BusinessException("Debes especificar que el paciente ha fallecido para detallar una fecha de deceso.")
                    if (confirmado.fechaDeceso.isBefore(confirmado.fechaEntregaResultado.toLocalDate()))
                        throw BusinessException("La fecha de deceso no puede ser antes de la fecha de entrega del resultado del examen.")
                }
            }
            return confirmadosRepository!!.saveAll(confirmados)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateConfirmados(confirmado: Confirmados): Confirmados {
        val opt: Optional<Confirmados>
        try {
            opt=confirmadosRepository!!.findById(confirmado.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la persona ${confirmado.id}")
        else{
            try {
                //validaciones
                if (opt.get().idPaciente != confirmado.idPaciente)
                    throw BusinessException("No puedes cambiar el id de paciente confirmado.")


                if (confirmado.fechaExamen!!.isAfter(LocalDateTime.now()))
                    throw BusinessException("La fecha de realizacion del examen debe ser antes de la fecha actual para un paciente confirmado.")
                if (confirmado.recuperado && confirmado.fallecido)
                    throw BusinessException("El paciente confirmado no puede estar recuperado y fallecido al mismo tiempo.")
                if (confirmado.recuperado && confirmado.fechaRecuperacion == null)
                    throw BusinessException("Se debe especificar la fecha en la que se recupero el paciente.")
                if (confirmado.fallecido && confirmado.fechaDeceso == null)
                    throw BusinessException("Se debe especificar la fecha en la que fallecio el paciente.")
                if (confirmado.recuperado && confirmado.fechaDeceso != null)
                    throw BusinessException("No se debe especificar la fecha de deceso en un paciente recuperado.")
                if (confirmado.fallecido && confirmado.fechaRecuperacion != null)
                    throw BusinessException("No se debe especificar la fecha de recuperacion de un paciente fallecido.")
                if (confirmado.fechaEntregaResultado!!.isBefore(confirmado.fechaExamen))
                    throw BusinessException("La fecha de entrega del resultado no puede ser antes de la fecha de realizacion del examen.")
                if (confirmado.fechaRecuperacion != null) {
                    if (!confirmado.recuperado)
                        throw BusinessException("Debes especificar que el paciente esta recuperado para detallar una fecha de recuperacion.")
                    if (confirmado.fechaRecuperacion.isBefore(confirmado.fechaEntregaResultado.toLocalDate()))
                        throw BusinessException("La fecha de recuperacion no puede ser antes de la fecha de entrega del resultado del examen.")
                }
                if (confirmado.fechaDeceso != null) {
                    if (!confirmado.fallecido)
                        throw BusinessException("Debes especificar que el paciente ha fallecido para detallar una fecha de deceso.")
                    if (confirmado.fechaDeceso.isBefore(confirmado.fechaEntregaResultado.toLocalDate()))
                        throw BusinessException("La fecha de deceso no puede ser antes de la fecha de entrega del resultado del examen.")
                }
                confirmadosRepository!!.save(confirmado)
            }catch (e1:Exception){
                throw BusinessException(e1.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun getConfirmadoByRecuperado(recuperado: Boolean): List<Confirmados> {
        val opt: Optional<List<Confirmados>>
        try {
            opt=confirmadosRepository!!.findByRecuperado(recuperado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontraron recuperados")
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getConfirmadoByFallecido(fallecido: Boolean): List<Confirmados> {
        val opt: Optional<List<Confirmados>>
        try {
            opt=confirmadosRepository!!.findByFallecido(fallecido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontraron fallecidos")
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getConfirmadoByIdPaciente(idPaciente: Int): Confirmados {
        val opt: Optional<Confirmados>
        try {
            opt=confirmadosRepository!!.findByIdPaciente(idPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el paciente confirmado con id de paciente: $idPaciente")
        return opt.get()
    }

}