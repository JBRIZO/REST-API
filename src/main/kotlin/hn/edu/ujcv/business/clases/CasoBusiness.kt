package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.ICasoBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.CasoRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.TipoCaso
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CasoBusiness: ICasoBusiness {
    @Autowired
    val casoRepository: CasoRepository?=null
    @Throws(BusinessException::class)
    override fun getTipoCaso(): List<TipoCaso> {
        try {
            return casoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getTipoCasoById(idTipoCaso: Int): TipoCaso {
        val opt: Optional<TipoCaso>
        try {
            opt=casoRepository!!.findById(idTipoCaso)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la persona $idTipoCaso")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveTipoCaso(tipoCaso: TipoCaso): TipoCaso {
        try {
            tipoCaso.tipo = tipoCaso.tipo.trimStart().trimEnd().lowercase()
            if (tipoCaso.tipo.isEmpty())
                throw BusinessException("El tipo no puede ir en blanco.")
            if (tipoCaso.tipo.length < 4)
                throw BusinessException("El tipo no puede ser tan corto.")
            if (tipoCaso.tipo.length >50)
                throw BusinessException("El tipo no puede contener tantos caracteres.")
            if (!tipoCaso.tipo.matches(Regex("^[^\\d]*\$")))
                throw BusinessException("El tipo no puede contener numeros.")
            if (Regex("(.)\\1{2,}").find(tipoCaso.tipo) != null)
                throw BusinessException("El tipo no puede contener tantas letras repetidas.")
            tipoCaso.tipo = tipoCaso.tipo.replaceFirstChar { it.uppercase() }
            return casoRepository!!.save(tipoCaso)
        }catch(e : DataIntegrityViolationException){
        throw BusinessException("Ya existe un tipo de caso con ese nombre.")
        }catch (e:Exception){
        throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveTipoCaso(tiposCaso: List<TipoCaso>): List<TipoCaso> {
        try {
            for (tipoCaso in tiposCaso){
                tipoCaso.tipo = tipoCaso.tipo.trimStart().trimEnd().lowercase()
                if (tipoCaso.tipo.isEmpty())
                    throw BusinessException("El tipo no puede ir en blanco.")
                if (tipoCaso.tipo.length < 4)
                    throw BusinessException("El tipo no puede ser tan corto.")
                if (tipoCaso.tipo.length >50)
                    throw BusinessException("El tipo no puede contener tantos caracteres.")
                if (!tipoCaso.tipo.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El tipo no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(tipoCaso.tipo) != null)
                    throw BusinessException("El tipo no puede contener tantas letras repetidas.")
                tipoCaso.tipo = tipoCaso.tipo.replaceFirstChar { it.uppercase() }
            }
            return casoRepository!!.saveAll(tiposCaso)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateTipoCaso(tipoCaso: TipoCaso): TipoCaso {
        val opt: Optional<TipoCaso>
        try {
            opt=casoRepository!!.findById(tipoCaso.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el caso con id: ${tipoCaso.id}")
        else{
            try {
                tipoCaso.tipo = tipoCaso.tipo.trimStart().trimEnd().lowercase()
                if (tipoCaso.tipo.isEmpty())
                    throw BusinessException("El tipo no puede ir en blanco.")
                if (tipoCaso.tipo.length < 4)
                    throw BusinessException("El tipo no puede ser tan corto.")
                if (tipoCaso.tipo.length >50)
                    throw BusinessException("El tipo no puede contener tantos caracteres.")
                if (!tipoCaso.tipo.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El tipo no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(tipoCaso.tipo) != null)
                    throw BusinessException("El tipo no puede contener tantas letras repetidas.")
                tipoCaso.tipo = tipoCaso.tipo.replaceFirstChar { it.uppercase() }

                casoRepository!!.save(tipoCaso)
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("Ya existe un tipo de caso con ese nombre.")
            }catch (e : Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()

    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun getTipoCasoByTipo(tipo: String): TipoCaso {
        val opt: Optional<TipoCaso>
        try {
            opt=casoRepository!!.findByTipo(tipo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el tipo de caso con nombre: $tipo")
        return opt.get()
    }


}