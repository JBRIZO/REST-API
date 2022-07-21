package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.ISintomasEvaluacionBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.SintomasEvaluacionRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.SintomasEvaluacion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class SintomasEvaluacionBusiness : ISintomasEvaluacionBusiness {
    @Autowired
    val sintomasEvaluacionRepository: SintomasEvaluacionRepository? = null

    @Throws(BusinessException::class)
    override fun getSintomasEvaluacion(): List<SintomasEvaluacion> {
        try {
            return sintomasEvaluacionRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSintomasEvaluacionById(idSintomaEvaluacion: Int): SintomasEvaluacion {
        val opt: Optional<SintomasEvaluacion>
        try {
            opt=sintomasEvaluacionRepository!!.findById(idSintomaEvaluacion)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el sintoma evaluacion con id $idSintomaEvaluacion")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveSintomasEvaluacion(sintomaEvaluacion: SintomasEvaluacion): SintomasEvaluacion {
        try {
            return sintomasEvaluacionRepository!!.save(sintomaEvaluacion)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ese sintoma ya esta asociado a esa evaluacion o ese sintoma o evaluacion no existe.")
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveSintomasEvaluacion(sintomasEvaluacion: List<SintomasEvaluacion>): List<SintomasEvaluacion> {
        try {
            return sintomasEvaluacionRepository!!.saveAll(sintomasEvaluacion)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ese sintoma ya esta asociado a esa evaluacion o ese sintoma o evaluacion no existe.")
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getByIdEvaluacion(idEvaluacion: Int): List<SintomasEvaluacion> {
        val opt: Optional<List<SintomasEvaluacion>>
        try {
            opt=sintomasEvaluacionRepository!!.findByIdEvaluacion(idEvaluacion)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontraron sintomas de evaluacion con id de evaluacion: $idEvaluacion")
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getByIdSintoma(idSintoma: Int): List<SintomasEvaluacion> {
        val opt: Optional<List<SintomasEvaluacion>>
        try {
            opt=sintomasEvaluacionRepository!!.findByIdSintoma(idSintoma)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontraron sintomas de evaluacion con id de sintoma:$idSintoma")
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeSintomasEvaluacion(idSintomasEvaluacion: Int) {
        val opt: Optional<SintomasEvaluacion>
        try {
            opt=sintomasEvaluacionRepository!!.findById(idSintomasEvaluacion)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el sintoma evaluacion con id: $idSintomasEvaluacion")
        else{
            try {
                sintomasEvaluacionRepository!!.deleteById(idSintomasEvaluacion)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}