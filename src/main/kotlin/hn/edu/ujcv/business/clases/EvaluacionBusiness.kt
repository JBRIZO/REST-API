package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IEvaluacionBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.EvaluacionRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Evaluacion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class EvaluacionBusiness: IEvaluacionBusiness {

        @Autowired
        val evaluacionRepository: EvaluacionRepository? = null

        @Throws(BusinessException::class)
        override fun getEvaluaciones(): List<Evaluacion> {
            try {
                return evaluacionRepository!!.findAll()
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }

        @Throws(BusinessException::class, NotFoundException::class)
        override fun getEvaluacionById(idEvaluacion: Int): Evaluacion {
            val opt: Optional<Evaluacion>
            try {
                opt=evaluacionRepository!!.findById(idEvaluacion)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
            if (!opt.isPresent)
                throw NotFoundException("No se encontro la evaluacion $idEvaluacion")
            return opt.get()
        }

    override fun getEvaluacionByIdUsuario(idUsuarioEvaluacion: Int): List<Evaluacion> {
        val opt: Optional<List<Evaluacion>>
        try {
            opt=evaluacionRepository!!.findByIdUsuario(idUsuarioEvaluacion)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la evaluacion $idUsuarioEvaluacion")
        return opt.get()
    }

    override fun getEvaluacionByIdPaciente(idPacienteEvaluacion: Int): Evaluacion {
        val opt: Optional<Evaluacion>
        try {
            opt=evaluacionRepository!!.findByIdPaciente(idPacienteEvaluacion)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la evaluacion $idPacienteEvaluacion")
        return opt.get()
    }

    override fun getEvaluacionByIdEstado(idEstadoEvaluacion: Int): List<Evaluacion> {
        val opt: Optional<List<Evaluacion>>
        try {
            opt=evaluacionRepository!!.findByIdEstado(idEstadoEvaluacion)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la evaluacion $idEstadoEvaluacion")
        return opt.get()
    }

    override fun getEvaluacionByIdCaso(idCasoEvaluacion: Int): List<Evaluacion> {
        val opt: Optional<List<Evaluacion>>
        try {
            opt=evaluacionRepository!!.findByIdTipoCaso(idCasoEvaluacion)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la evaluacion $idCasoEvaluacion")
        return opt.get()
    }

    @Throws(BusinessException::class)
        override fun saveEvaluacion(evaluacion: Evaluacion): Evaluacion {
            try {
                //fecha y hora
                if(evaluacion.fechaHora!!.isAfter(LocalDateTime.now()))
                    throw BusinessException("La fecha de la evaluacion no puede ser despues de la fecha actual")
                //comentarios
                evaluacion.comentarios = evaluacion.comentarios.trimStart().trimEnd().lowercase()
                if (evaluacion.comentarios.length > 250)
                    throw BusinessException("Los comentarios no pueden exceder los 250 caracteres.")
                if (Regex("(.)\\1{4,}").find(evaluacion.comentarios) != null)
                    throw BusinessException("No puedes repetir un caracter tantas veces en los comentarios.")
                evaluacion.comentarios = evaluacion.comentarios.replaceFirstChar { it.uppercase() }

                return evaluacionRepository!!.save(evaluacion)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        @Throws(BusinessException::class)
        override fun saveEvaluaciones(evaluaciones: List<Evaluacion>): List<Evaluacion> {
            try {
                for (evaluacion in evaluaciones){
                    //fecha y hora
                    if(evaluacion.fechaHora!!.isAfter(LocalDateTime.now()))
                        throw BusinessException("La fecha de la evaluacion no puede ser despues de la fecha actual")
                    //comentarios
                    evaluacion.comentarios = evaluacion.comentarios.trimStart().trimEnd().lowercase()
                    if (evaluacion.comentarios.length > 250)
                        throw BusinessException("Los comentarios no pueden exceder los 250 caracteres.")
                    if (Regex("(.)\\1{4,}").find(evaluacion.comentarios) != null)
                        throw BusinessException("No puedes repetir un caracter tantas veces en los comentarios.")
                    evaluacion.comentarios = evaluacion.comentarios.replaceFirstChar { it.uppercase() }

                }
                return evaluacionRepository!!.saveAll(evaluaciones)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }

        @Throws(BusinessException::class, NotFoundException::class)
        override fun updateEvaluacion(evaluacion: Evaluacion): Evaluacion {
            val opt: Optional<Evaluacion>
            try {
                opt=evaluacionRepository!!.findById(evaluacion.id)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
            if (!opt.isPresent)
                throw NotFoundException("No se encontro la evaluacion ${evaluacion.id}")
            else{
                try {
                    //validaciones
                    if (opt.get().idPaciente != evaluacion.idPaciente)
                        throw BusinessException("No puedes cambiar el paciente asociado a esta evaluacion")
                    if (opt.get().idUsuario != evaluacion.idUsuario)
                        throw BusinessException("No puedes cambiar el usuario que realizo esta evaluacion.")
                    //fecha y hora
                    if(evaluacion.fechaHora!!.isAfter(LocalDateTime.now()))
                        throw BusinessException("La fecha de la evaluacion no puede ser despues de la fecha actual")
                    //comentarios
                    evaluacion.comentarios = evaluacion.comentarios.trimStart().trimEnd().lowercase()
                    if (evaluacion.comentarios.length > 250)
                        throw BusinessException("Los comentarios no pueden exceder los 250 caracteres.")
                    if (Regex("(.)\\1{4,}").find(evaluacion.comentarios) != null)
                        throw BusinessException("No puedes repetir un caracter tantas veces en los comentarios.")
                    evaluacion.comentarios = evaluacion.comentarios.replaceFirstChar { it.uppercase() }
                    evaluacionRepository!!.save(evaluacion)
                }catch (e1:Exception){
                    throw BusinessException(e1.message)
                }
            }
            return opt.get()
        }

}