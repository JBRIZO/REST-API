package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IColoniaBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.ColoniaRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Colonias
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*
import javax.xml.crypto.Data

@Service
class ColoniaBusiness: IColoniaBusiness {

    @Autowired
    val coloniaRepository:ColoniaRepository? = null

    @Throws(BusinessException::class)
    override fun getColonia(): List<Colonias> {
        try {
            return coloniaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getColoniaById(idColonia: Int): Colonias {
        val opt: Optional<Colonias>
        try {
            opt=coloniaRepository!!.findById(idColonia)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la colonia con id $idColonia")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveColonia(colonia: Colonias): Colonias {
        try {
            colonia.nombre = colonia.nombre.trimEnd().trimStart().lowercase()
            if (colonia.nombre.isEmpty())
                throw BusinessException("El nombre no puede ir en blanco.")
            if (colonia.nombre.length < 5)
                throw BusinessException("El nombre no puede ser tan corto.")
            if (colonia.nombre.length >50)
                throw BusinessException("El nombre no puede contener tantos caracteres.")
            if (colonia.nombre.matches(Regex("^[\\d ]*\$")))
                throw BusinessException("El nombre de la colonia no puede contener solo numeros.")
            if (Regex("(.)\\1{2,}").find(colonia.nombre) != null)
                throw BusinessException("El nombre no puede contener tantas letras repetidas.")
            colonia.nombre = colonia.nombre.replaceFirstChar { it.uppercase() }
            return coloniaRepository!!.save(colonia)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe una colonia con ese nombre.")
        }catch (e : Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveColonias(colonias: List<Colonias>): List<Colonias> {
        try {
            for (colonia in colonias){
                if (colonia.nombre.isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (colonia.nombre.length < 5)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (colonia.nombre.length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (colonia.nombre.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre de la colonia no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(colonia.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
            }
            return coloniaRepository!!.saveAll(colonias)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe una colonia con ese nombre.")
        }catch (e : Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateColonia(colonia: Colonias): Colonias {
        val opt: Optional<Colonias>
        try {
            opt=coloniaRepository!!.findById(colonia.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro la colonia con id: ${colonia.id}")
        else{
            try {
                if (colonia.nombre.isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (colonia.nombre.length < 5)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (colonia.nombre.length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (colonia.nombre.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre de la colonia no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(colonia.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("Ya existe una colonia con ese nombre.")
            }catch (e : Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun getColoniaByNombre(nombreColonia: String): Colonias {
        val opt: Optional<Colonias>
        try{
            opt = coloniaRepository!!.findByNombre(nombreColonia)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro colonia con el nombre: $nombreColonia")
        return opt.get()
    }
}